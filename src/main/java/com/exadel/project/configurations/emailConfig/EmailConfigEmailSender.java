package com.exadel.project.configurations.emailConfig;

import com.exadel.project.common.mailSender.EmailService;
import com.exadel.project.common.mailSender.EmailServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class EmailConfigEmailSender implements EmailServiceBeanGetter {

    @Bean
    @ConditionalOnProperty(prefix = "notification", name = "service", havingValue = "email")
    public EmailService emailNotificationSender() {
        return new EmailServiceImpl();
    }
}
