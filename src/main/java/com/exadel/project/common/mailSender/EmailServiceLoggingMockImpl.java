package com.exadel.project.common.mailSender;

import com.exadel.project.internship.entity.Internship;
import com.exadel.project.interview.entity.Interview;
import com.exadel.project.trainee.entity.Trainee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

import java.util.Objects;


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

    public void sendSimpleMessageUsingTemplate(String to,
                                               String subject,
                                               String[] dataToTemplate) {
        String text = String.format(Objects.requireNonNull(template.getText()), dataToTemplate);
        sendSimpleMessage(to, subject, text);
    }

    @Override
    public void sendHTMLMail(String address) {
        logger.info("attempt to send email with welcome.html template to next email {}", address);
    }

    @Override
    public void sendHTMLBasedConfirmEmail(Internship internship, Trainee trainee, String approveUrl) {
        logger.info("An attempt to send email with a html template after user registration for an email confirmation.");
    }

    @Override
    public void sendHTMLInternshipAnnouncementEmail(Internship internship, Trainee trainee, String internshipUrl, String traineeUrl) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("An attempt to send emails with a html internship announcement template after internship publication.")
                .append("\nInternship ID: ").append(internship.getId())
                .append("\nInternship URL: ").append(internshipUrl)
                .append("\nTrainee email: ").append(trainee.getEmail());
        logger.info(stringBuffer.toString());
    }

    @Override
    public void sendHTMLInterviewReminderEmail(Interview interview) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("An attempt to send email with a html template reminder about interview to a trainee.")
                .append("\nInterview ID: ").append(interview.getId())
                .append("\nInternship ID: ").append(interview.getInternship().getId())
                .append("\nTrainee email: ").append(interview.getTrainee().getEmail());
        logger.info(stringBuffer.toString());
    }

    @Override
    public void sendHTMLInterviewReminderEmailWithFeedback(Interview interview, String interviewFeedbackUrl) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("An attempt to send email with a html template reminder about interview to an interviewer.")
                .append("\nInterview ID: ").append(interview.getId())
                .append("\nInternship ID: ").append(interview.getInternship().getId())
                .append("\nInterviewer email: ").append(interview.getInterviewer().getEmail());
        logger.info(stringBuffer.toString());
    }
}