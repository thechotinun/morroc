package com.morroc.v1.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationConfig {

    private String name;
    private int port;
    private String googleClientId;
    private String googleClientSecret;
    private String googleRedirectUri;
    private String jwtSecret;
    private int jwtExpiration;
}