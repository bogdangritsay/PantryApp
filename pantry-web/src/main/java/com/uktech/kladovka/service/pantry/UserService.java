package com.uktech.kladovka.service.pantry;




import com.uktech.kladovka.service.mail.token.ConfirmationTokenService;
import com.uktech.kladovka.model.domain.ConfirmationToken;
import com.uktech.kladovka.model.domain.User;
import com.uktech.kladovka.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService implements UserDetailsService {
    private static final String USER_NOT_FOUND_MSG = "User %s is not found!";
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("Email already exists");
        }

        String encodedPassword =  bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public void saveUser(User user) {
     userRepository.save(user);
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public void updateProfile(User user, String password) {
        if(!StringUtils.isEmpty(password)) {
            String encodedPassword =  bCryptPasswordEncoder.encode(password);

            user.setPassword(encodedPassword);
            userRepository.save(user);
        }
    }

    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }
}
