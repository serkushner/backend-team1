package com.exadel.project.common.mailSender;

import com.exadel.project.internship.entity.Internship;
import com.exadel.project.trainee.entity.Trainee;

import javax.mail.MessagingException;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendSimpleMessageUsingTemplate(String to, String subject,
                                        String ...templateModel);

    void sendHTMLMail() throws MessagingException;

    void sendHTMLBasedConfirmEmail(Internship internship, Trainee trainee, String approveUrl);
}
