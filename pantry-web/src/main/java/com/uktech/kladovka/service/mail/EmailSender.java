package com.uktech.kladovka.service.mail;

import java.util.Map;

public interface EmailSender {
    void sendEmail(String toAddress, String subject, String templateName,  Map<String, Object> model);
}