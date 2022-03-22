package com.uktech.kladovka.service.pantry;


import com.uktech.pantry.domain.Role;
import com.uktech.pantry.domain.User;
import com.uktech.pantry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByUsername(name);
    }

    public boolean addUser(User user)
    {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if(existingUser!= null)
        {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(user.getPassword());
        userRepository.save(user);
        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(long userId) {
        return userRepository.findById(userId).get();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for(String key: form.keySet())
        {
            if(roles.contains(key))
            {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public void updateProfile(User user, String password) {
        if(!StringUtils.isEmpty(password))
        {
            user.setPassword(password);
            userRepository.save(user);
        }
    }
}
