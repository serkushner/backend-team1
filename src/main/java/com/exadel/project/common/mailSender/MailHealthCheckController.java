package com.exadel.project.common.mailSender;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mailHealthCheck")
@RequiredArgsConstructor
public class MailHealthCheckController {

    @Qualifier("emailNotification")
    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/sendTo")
    public ResponseEntity<String> sendEmail(
            @RequestParam(value = "email") String address,
            @RequestParam(value = "topic", required = false, defaultValue = "exadel emailSender") String topic,
            @RequestParam(value = "text", required = false, defaultValue = "Hello from exadel emailSender!") String text) {
        emailService.sendSimpleMessage(address, topic, text);
        return ResponseEntity.ok("email was sent");
    }


    @GetMapping(value = "/sendToWithTemplate")
    public ResponseEntity<String> sendEmailWithTemplate(
            @RequestParam(value = "email") String address,
            @RequestParam(value = "topic", required = false, defaultValue = "exadel emailSender") String topic,
            @RequestParam(value = "text", defaultValue = "data in template from exadel emailSender") String text) {
        emailService.sendSimpleMessageUsingTemplate(address, topic, text);
        return ResponseEntity.ok("email was sent");
    }

}
