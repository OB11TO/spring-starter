package com.ob11to.spring;

import com.ob11to.spring.config.ApplicationConfiguration;
import com.ob11to.spring.database.pool.ConnectionPool;
import com.ob11to.spring.database.repository.CompanyRepository;
import com.ob11to.spring.database.repository.CrudRepository;
import com.ob11to.spring.service.CompanyService;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;


public class ApplicationRunner {

    public static void main(String[] args) {

        String value = "hello";
        System.out.println(CharSequence.class.isAssignableFrom(value.getClass()));
        System.out.println(BeanFactoryPostProcessor.class.isAssignableFrom(value.getClass()));
        System.out.println(Serializable.class.isAssignableFrom(value.getClass()));

        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
//            context.register(ApplicationConfiguration.class);
//            context.getEnvironment().setActiveProfiles("web", "prod");
//            context.refresh();

            //        clazz -> String -> Map<String, Object>
            var connectionPool1 = context.getBean("pool1", ConnectionPool.class);
            System.out.println(connectionPool1);

            var companyService = context.getBean(CompanyService.class);
            System.out.println(companyService.findById(1));
        }
    }
}
