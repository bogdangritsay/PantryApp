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
    private static final String FAILED_TO_SEND_EMAIL_MSG = "Failed to send email!";
    private static final String FROM_EMAIL = "pantry.notifications@gmail.com";
    private static final String EMAIL_TEMPLATE_FOLDER_PATH = "email_templates/";
    private final static Logger log = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    Configuration freeMakerConfiguration;


    @Override
    @Async
    public void sendEmail(String toAddress, String subject, String templateName, Map<String, Object> model)  {
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
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setFrom(FROM_EMAIL);
            helper.setText(geContentFromTemplate(model, EMAIL_TEMPLATE_FOLDER_PATH + templateName), true);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error(FAILED_TO_SEND_EMAIL_MSG, e);
            throw new IllegalStateException(FAILED_TO_SEND_EMAIL_MSG);
        }
    }

    public String geContentFromTemplate(Map<String, Object > model, String templatePath) {
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(freeMakerConfiguration.getTemplate(templatePath), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}