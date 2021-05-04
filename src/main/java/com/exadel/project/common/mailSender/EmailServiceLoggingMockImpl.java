package com.exadel.project.common.mailSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmailServiceLoggingMockImpl implements EmailService {

    private static final String NOREPLY_ADDRESS = "noreply_exadeltestsender@gmail.com";
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceLoggingMockImpl.class);

    private SimpleMailMessage template;

    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("This is the email template for email:\n%s\n");
        this.template = message;
    }

    public void sendSimpleMessage(String to, String subject, String text) throws MailException {
            SimpleMailMessage message = new SimpleMailMessage();
            StringBuffer stringBuffer = new StringBuffer();
            String DELIMITER = "; \n";
            stringBuffer.append("messageFromAddress: ").append(NOREPLY_ADDRESS).append(DELIMITER);
            stringBuffer.append("messageToAddress: ").append(to).append(DELIMITER);
            stringBuffer.append("messageTopic: ").append(subject).append(DELIMITER);
            stringBuffer.append("messageText: ").append(text).append(DELIMITER);
            logger.info(stringBuffer.toString());
    }

    @Override
    public void sendSimpleMessageUsingTemplate(String to,
                                               String subject,
                                               String[] dataToTemplate) {
        String text = String.format(Objects.requireNonNull(template.getText()), dataToTemplate);
        sendSimpleMessage(to, subject, text);
    }
}
