package com.exadel.project.common.mailSender;

import com.exadel.project.common.exception.EmailTextException;
import com.exadel.project.internship.entity.Internship;
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

    public void sendHTMLBasedConfirmEmail(Internship internship,
                                          Trainee trainee,
                                          String approveUrl){
        Context context = new Context();
        context.setVariable("internship", internship);
        context.setVariable("trainee", trainee);
        context.setVariable("approveUrl", approveUrl);

        String process = templateEngine.process("approveTraineeEmail.html", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject("Exadel internship email confirmation");
            helper.setText(process, true);
            helper.setTo(trainee.getEmail());
            helper.setFrom(NOREPLY_ADDRESS);
        } catch (MessagingException exception) {
            throw new EmailTextException(exception);
        }
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendHTMLInternshipAnnouncementEmail(Internship internship, Trainee trainee, String internshipUrl) {
        Context context = new Context();
        context.setVariable("internship", internship);
        context.setVariable("trainee", trainee);
        context.setVariable("internshipUrl", internshipUrl);

        String process = templateEngine.process("internshipAnnouncement.html", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject("Exadel internship announcement");
            helper.setText(process, true);
            helper.setTo(trainee.getEmail());
            helper.setFrom(NOREPLY_ADDRESS);
        } catch (MessagingException exception) {
            throw new EmailTextException(exception);
        }
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendHTMLMail(String toEmail) {
        Context context = new Context();
        context.setVariable("toEmail", toEmail);

        String process = templateEngine.process("welcome.html", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject("Привет, это тестовая отправка письма с HTML шаблоном");
            helper.setText(process, true);
            helper.setTo(toEmail);
        } catch (MessagingException exception) {
            throw new EmailTextException(exception);
        }
        javaMailSender.send(mimeMessage);
    }
}
