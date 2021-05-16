package com.exadel.project.common.mailSender;

import com.exadel.project.common.exception.EmailTextException;
import com.exadel.project.internship.entity.Internship;
import com.exadel.project.trainee.entity.Trainee;
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

//    public void sendMIMEMail() throws MessagingException {
//        User user = getUser("Ivan", "Ivanov", "ivaniva@gmail.com");
//
//        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
//        helper.setSubject("Welcome " + user.getName());
//
//        String html = "<!doctype html>\n" +
//                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
//                "      xmlns:th=\"http://www.thymeleaf.org\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <meta name=\"viewport\"\n" +
//                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
//                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
//                "    <title>Email</title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<div>Welcome <b>" + user.getName() + "</b></div>\n" +
//                "\n" +
//                "<div> Your username is <b>" + user.getUsername() + "</b></div>\n" +
//                "</body>\n" +
//                "</html>\n";
//        helper.setText(html, true);
//        helper.setTo(user.getEmail());
//        javaMailSender.send(mimeMessage);
//    }

    public void sendHTMLBasedConfirmEmail(Internship internship,
                                          Trainee trainee,
                                          String approveUrl){
        Context context = new Context();
        context.setVariable("internship", internship);
        context.setVariable("trainee", trainee);
        context.setVariable("approveUrl", approveUrl);

        String process = templateEngine.process("welcome.html", context);
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
    public void sendHTMLMail() throws MessagingException {
        User user = getUser("Ivan", "Ivanov", "shpaser2@yandex.ru");

        Context context = new Context();
        context.setVariable("user", user);

        String process = templateEngine.process("welcome.html", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Привет " + user.getName());
        helper.setText(process, true);
        helper.setTo(user.getEmail());
        javaMailSender.send(mimeMessage);
    }

    public User getUser(String name, String username, String email) {
        return new User(name, username, email);
    }

    private class User {
        private String name;
        private String username;
        private String email;

        public User(String name, String username, String email) {
            this.name = name;
            this.username = username;
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
