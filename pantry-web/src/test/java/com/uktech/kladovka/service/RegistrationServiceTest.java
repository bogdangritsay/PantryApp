package com.uktech.kladovka.service;

import com.uktech.kladovka.model.domain.ConfirmationToken;
import com.uktech.kladovka.model.domain.Role;
import com.uktech.kladovka.model.domain.User;
import com.uktech.kladovka.service.mail.EmailSender;
import com.uktech.kladovka.service.mail.EmailValidator;
import com.uktech.kladovka.service.mail.token.ConfirmationTokenService;
import com.uktech.kladovka.service.pantry.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {

    @Mock
    private EmailValidator emailValidator;

    @Mock
    private UserService userService;

    @Mock
    private ConfirmationTokenService confirmationTokenService;

    @Mock
    private EmailSender emailSender;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterWithValidEmailShouldReturnToken() {
        User user = new User("John", "Doe", "Middle", "password", Role.USER,
                "Address", "john.doe@example.com", "DefaultSite", "123456", "passwordConfirm", null);
        when(emailValidator.test(user.getEmail())).thenReturn(true);
        when(userService.signUpUser(any(User.class))).thenReturn("token");

        String token = registrationService.register(user);

        assertNotNull(token);
        assertEquals("token", token);
        verify(emailSender, times(1)).sendEmail(anyString(), anyString(), anyString(), anyMap());
    }

    @Test
    void testRegisterWithInvalidEmailShouldThrowIllegalStateException() {
        User user = new User("John", "Doe", "Middle", "password", Role.USER,
                "Address", "invalid-email", "DefaultSite", "123456", "passwordConfirm", null);
        when(emailValidator.test(user.getEmail())).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> registrationService.register(user));
        verifyNoInteractions(userService);
        verifyNoInteractions(emailSender);
    }

    @Test
    void testConfirmTokenWithValidTokenShouldConfirmTokenAndEnableUser() {
        String token = "valid-token";
        User user = new User();
        LocalDateTime now = LocalDateTime.now();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, now, now.plusDays(1), user);
        when(confirmationTokenService.getToken(token)).thenReturn(confirmationToken);
        when(confirmationTokenService.setConfirmedAt(token)).thenAnswer(invocation -> {
            confirmationToken.setConfirmedAt(LocalDateTime.now());
            return 1;
        });
        when(userService.enableAppUser(confirmationToken.getUser().getEmail())).thenReturn(1);

        assertDoesNotThrow(() -> registrationService.confirmToken(token));

        assertNotNull(confirmationToken.getConfirmedAt());
        verify(userService, times(1)).enableAppUser(confirmationToken.getUser().getEmail());
    }

    @Test
    void testConfirmTokenWithAlreadyConfirmedTokenShouldThrowIllegalStateException() {
        String token = "already-confirmed-token";
        User user = new User();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusDays(1), user);
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        when(confirmationTokenService.getToken(token)).thenReturn(confirmationToken);

        assertThrows(IllegalStateException.class, () -> registrationService.confirmToken(token));
        verifyNoInteractions(userService);
    }

    @Test
    void testConfirmTokenWithExpiredTokenShouldThrowIllegalStateException() {
        String token = "expired-token";
        User user = new User();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().minusDays(1), user);
        when(confirmationTokenService.getToken(token)).thenReturn(confirmationToken);

        assertThrows(IllegalStateException.class, () -> registrationService.confirmToken(token));
        verifyNoInteractions(userService);
    }
}
