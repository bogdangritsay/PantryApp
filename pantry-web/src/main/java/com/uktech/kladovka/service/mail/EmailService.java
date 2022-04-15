package com.uktech.kladovka.service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailService implements EmailSender {
    private static final String FAILED_TO_SEND_EMAIL_MSG = "Failed to send email!";
    private static final String FROM_EMAIL = "pantry.notifications@gmail.com";
    private final static Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    public JavaMailSender emailSender;

    @Override
    @Async
    public void sendEmail(String toAddress, String subject, String message)  {
        //TODO: can be usefull for sendings orders
       /* MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(toAddress);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);
        FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
        messageHelper.addAttachment("Purchase Order", file);
        emailSender.send(mimeMessage);*/

        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(message, true);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setFrom(FROM_EMAIL);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error(FAILED_TO_SEND_EMAIL_MSG, e);
            throw new IllegalStateException(FAILED_TO_SEND_EMAIL_MSG);
        }
    }

}