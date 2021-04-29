package com.exadel.project.common.mailSender;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendSimpleMessageUsingTemplate(String to, String subject,
                                        String ...templateModel);
}
