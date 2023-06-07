package com.uktech.kladovka.service;

import com.uktech.kladovka.service.mail.EmailSender;
import com.uktech.kladovka.service.mail.EmailValidator;
import com.uktech.kladovka.service.mail.token.ConfirmationTokenService;
import com.uktech.kladovka.service.pantry.UserService;
import com.uktech.kladovka.model.domain.ConfirmationToken;
import com.uktech.kladovka.model.domain.Role;
import com.uktech.kladovka.model.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegistrationService {
    private final String EMAIL_NOT_VALID_MSG = "Email not valid";
    private final String EMAIL_ALREADY_CONFIRMED_MSG = "Email already confirmed";
    private final String TOKEN_EXPIRED_MSG = "Token expired";
    private final String CONFIRM_YOUR_EMAIL_HDR = "PantryApp - Confirm your email";
    private final String CONFIRMATION_EMAIL_TEMPLATE = "confirmation_email.ftl";

    //TODO: change to universal
    private String confirmationLink = "http://localhost:8282/registration/confirm?token=";
    @Autowired
    private EmailValidator emailValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    private EmailSender emailSender;


    public String register(User user) {
        boolean isEmailValid = emailValidator.test(user.getEmail());

        if (!isEmailValid) {
            throw new IllegalStateException(EMAIL_NOT_VALID_MSG);
        }

        String token =  userService.signUpUser(
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

        confirmationLink+= token;
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getFirstName());
        model.put("confirmationLink", confirmationLink);

        emailSender.sendEmail(
                        user.getEmail(),
                        CONFIRM_YOUR_EMAIL_HDR,
                        CONFIRMATION_EMAIL_TEMPLATE,
                        model
        );
        return token;
    }

    @Transactional
    public void  confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException(EMAIL_ALREADY_CONFIRMED_MSG);
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException(TOKEN_EXPIRED_MSG);
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(confirmationToken.getUser().getEmail());
    }


}
