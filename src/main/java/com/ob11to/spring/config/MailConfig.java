package com.ob11to.spring.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MailConfig {

    @Value("${config.mail.sender}")
    private String mailSender;
}
