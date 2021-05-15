package com.exadel.project.common.mailSender;

import javax.mail.MessagingException;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendSimpleMessageUsingTemplate(String to, String subject,
                                        String ...templateModel);

    void sendHTMLMail() throws MessagingException;
}
