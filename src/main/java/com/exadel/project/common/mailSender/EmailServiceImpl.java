package com.exadel.project.common.mailSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service("EmailService")
public class EmailServiceImpl implements EmailService {

    private static final String NOREPLY_ADDRESS = "noreply_exadeltestsender@gmail.com";

    @Autowired
    private JavaMailSender emailSender;

    private SimpleMailMessage template;

    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("This is the email template for email:\n%s\n");
        this.template = message;
    }

    public void sendSimpleMessage(String to, String subject, String text) throws MailException {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(NOREPLY_ADDRESS);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
    }

    @Override
    public void sendSimpleMessageUsingTemplate(String to,
                                               String subject,
                                               String[] dataToTemplate) {
        String text = String.format(Objects.requireNonNull(template.getText()), dataToTemplate);
        sendSimpleMessage(to, subject, text);
    }
}
