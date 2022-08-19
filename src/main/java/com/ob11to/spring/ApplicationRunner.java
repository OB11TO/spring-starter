package com.ob11to.spring;

import com.ob11to.spring.database.repository.CrudRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ApplicationRunner {

    public static void main(String[] args) {
        var context = SpringApplication.run(ApplicationRunner.class, args);
        System.out.println(context.getBean(CrudRepository.class));
        System.out.println(context.getBeanDefinitionCount());
    }
}
