package com.exadel.project.common.mailSender;

import com.exadel.project.common.exception.EmailTextException;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.trainee.entity.Trainee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private static final String NOREPLY_ADDRESS = "noreply_exadeltestsender@gmail.com";

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;
    private SimpleMailMessage simpleTemplate;
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("This is the email template for email:\n%s\n");
        this.simpleTemplate = message;
    }

    public void sendSimpleMessage(String to, String subject, String text) throws MailException {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(NOREPLY_ADDRESS);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
    }

    @Override
    public void sendSimpleMessageUsingTemplate(String to,
                                               String subject,
                                               String[] dataToTemplate) {
        String text = String.format(Objects.requireNonNull(simpleTemplate.getText()), dataToTemplate);
        sendSimpleMessage(to, subject, text);
    }

    @Override
    public void sendHTMLBasedConfirmEmail(Internship internship,
                                          Trainee trainee,
                                          String approveUrl){
        Context context = new Context();
        context.setVariable("internship", internship);
        context.setVariable("trainee", trainee);
        context.setVariable("approveUrl", approveUrl);

        String process = templateEngine.process("approveTraineeEmail.html", context);
        sendHTMLMessageUsingTemplate(trainee.getEmail(), "Exadel internship email confirmation", process);
    }

    @Override
    public void sendHTMLInternshipAnnouncementEmail(Internship internship, Trainee trainee, String internshipUrl, String traineeUrl) {
        Context context = new Context();
        context.setVariable("internship", internship);
        context.setVariable("trainee", trainee);
        context.setVariable("internshipUrl", internshipUrl);
        context.setVariable("traineeUrl", traineeUrl);
        String process = templateEngine.process("internshipAnnouncement.html", context);
        sendHTMLMessageUsingTemplate(trainee.getEmail(), "Exadel internship announcement", process);
    }

    @Override
    public void sendHTMLInterviewReminderEmail(Interview interview) {
        Context context = new Context();
        context.setVariable("interview", interview);
        String process = templateEngine.process("interviewTraineeReminderEmail.html", context);
        sendHTMLMessageUsingTemplate(interview.getTrainee().getEmail(), "Exadel interview reminder", process);
    }

    @Override
    public void sendHTMLInterviewReminderEmailWithFeedback(Interview interview, String interviewFeedbackUrl) {
        Context context = new Context();
        context.setVariable("interview", interview);
        context.setVariable("feedbackUrl", interviewFeedbackUrl);
        String process = templateEngine.process("interviewReminderWithFeedbackLinkEmail.html", context);
        sendHTMLMessageUsingTemplate(interview.getInterviewer().getEmail(), "Exadel interview reminder", process);
    }

    @Override
    public void sendHTMLMail(String toEmail) {
        Context context = new Context();
        context.setVariable("toEmail", toEmail);
        String process = templateEngine.process("welcome.html", context);
        sendHTMLMessageUsingTemplate(toEmail,
                "Привет, это тестовая отправка письма с HTML шаблоном",
                process);
    }

    private void sendHTMLMessageUsingTemplate(String to,
                                              String subject,
                                              String text) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.setTo(to);
            helper.setFrom(NOREPLY_ADDRESS);
        } catch (MessagingException exception) {
            throw new EmailTextException(exception);
        }
        javaMailSender.send(mimeMessage);
        //TODO delete it or make it work
//        context.clearVariables();
    }
}
