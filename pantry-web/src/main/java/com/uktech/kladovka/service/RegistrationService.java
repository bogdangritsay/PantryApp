package com.uktech.kladovka.service;

import com.uktech.kladovka.service.mail.EmailValidator;
import com.uktech.kladovka.service.pantry.UserService;
import com.uktech.pantry.domain.Role;
import com.uktech.pantry.domain.User;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final EmailValidator emailValidator;
    private final UserService userService;

    public boolean register(User user) {
        boolean isEmailValid = emailValidator.test(user.getEmail());

        if (!isEmailValid) {
            throw new IllegalStateException("Email not valid");
        }

        return userService.signUpUser(
                new User(
                        user.getFirstName(),
                        user.getLastName(),
                        user.getMiddleName(),
                        user.getPassword(),
                        Role.USER,
                        user.getAddress(),
                        user.getEmail(),
                        user.getDefaultSite(),
                        user.getPhone(),
                        user.getPasswordConfirm(),
                        //TODO: initialize default pantry for user
                        null
        )
        );
    }
}
