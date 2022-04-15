package com.uktech.kladovka.service.mail;

public interface EmailSender {
    void sendEmail(String toAddress, String subject,  String message);
}