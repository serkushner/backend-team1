package com.exadel.project.configurations.emailConfig;

import com.exadel.project.common.mailSender.EmailService;

public interface EmailServiceBeanGetter {

    public EmailService emailNotificationSender();
}
