package com.uktech.kladovka.service.mail.token;

import com.uktech.pantry.domain.ConfirmationToken;
import com.uktech.pantry.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConfirmationTokenService {
    private final String TOKEN_NOT_FOUND_MSG = "Token not found!";
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;


    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public ConfirmationToken getToken(String token) {
        return confirmationTokenRepository.findByToken(token).orElseThrow(() -> new IllegalStateException(TOKEN_NOT_FOUND_MSG));
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

}
