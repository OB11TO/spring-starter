package com.ob11to.spring.config;

import com.ob11to.spring.config.condition.JpaConditional;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Conditional(JpaConditional.class)
public class JpaConfiguration {

//    @Bean
//    @ConfigurationProperties(prefix = "db")
//    public DataBaseProperties properties(){
//        return new DataBaseProperties();
//    }

    @PostConstruct
    void init() {
        System.out.println("Jpa configuration is enabled");
    }
}
