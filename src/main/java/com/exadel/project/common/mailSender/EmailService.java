package com.exadel.project.common.mailSender;

import com.exadel.project.internship.entity.Internship;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.trainee.entity.Trainee;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendSimpleMessageUsingTemplate(String to, String subject,
                                        String ...templateModel);

    void sendHTMLMail(String toEmail);

    void sendHTMLBasedConfirmEmail(Internship internship,
                                   Trainee trainee,
                                   String approveUrl);

    void sendHTMLInternshipAnnouncementEmail(Internship internship,
                                             Trainee trainee,
                                             String internshipUrl);

    void sendHTMLInterviewReminderEmail(Interview interview);

    void sendHTMLInterviewReminderEmailWithFeedback(Interview interview,
                                                    String interviewFeedbackUrl);
}
