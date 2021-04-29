package com.exadel.project.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "keycloak")
@Data
public class KeycloakConfigProperties {

    private String clientId;
    private String clientSecret;
    private String grantType;
    private String realm;
    private String authServerUrl;
    private String scope;

}
