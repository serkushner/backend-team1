package com.exadel.project.configurations;

import com.exadel.project.common.mailSender.EmailService;
import com.exadel.project.common.mailSender.EmailServiceImpl;
import com.exadel.project.common.mailSender.EmailServiceLoggingMockImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class EmailConfig {

    @Bean(name = "emailNotification")
    @ConditionalOnProperty(prefix = "notification", name = "service", havingValue = "email")
    public EmailService notificationSender() {
        return new EmailServiceImpl();
    }

    @Bean(name = "emailNotification")
    @ConditionalOnProperty(prefix = "notification", name = "service", havingValue = "logger",
            matchIfMissing = true)
    public EmailService notificationSender2() {
        return new EmailServiceLoggingMockImpl();
    }
}
