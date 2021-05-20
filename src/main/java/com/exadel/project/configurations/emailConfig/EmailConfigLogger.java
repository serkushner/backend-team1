package com.exadel.project.configurations.emailConfig;

import com.exadel.project.common.mailSender.EmailService;
import com.exadel.project.common.mailSender.EmailServiceLoggingMockImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class EmailConfigLogger {

    @Bean
    @ConditionalOnProperty(prefix = "notification", name = "service", havingValue = "logger",
            matchIfMissing = true)
    public EmailService emailNotificationLogger() {
        return new EmailServiceLoggingMockImpl();
    }
}
