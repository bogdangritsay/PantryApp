package com.uktech.kladovka.service.mail;

import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.util.Map;

@Service
public class EmailService implements EmailSender {
    public static final String FAILED_TO_SEND_EMAIL_MSG = "Failed to send email!";
    public static final String FROM_EMAIL = "pantry.notifications@gmail.com";
    public static final String EMAIL_TEMPLATE_FOLDER_PATH = "email_templates/";
    public final static Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    Configuration freeMakerConfiguration;

    @Override
    @Async
    public void sendEmail(String toAddress, String subject, String templateName, Map<String, Object> model)  {

        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setFrom(FROM_EMAIL);
            helper.setText(getContentFromTemplate(model, EMAIL_TEMPLATE_FOLDER_PATH + templateName), true);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error(FAILED_TO_SEND_EMAIL_MSG, e);
            throw new IllegalStateException(FAILED_TO_SEND_EMAIL_MSG);
        }
    }

    public String getContentFromTemplate(Map<String, Object > model, String templatePath) {
        StringBuilder content = new StringBuilder();

        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(freeMakerConfiguration.getTemplate(templatePath), model));
        } catch (Exception e) {
            log.error("Failed to process email template: " + templatePath, e);
            throw new IllegalStateException("Failed to process email template: " + templatePath, e);
        }
        return content.toString();
    }
}
