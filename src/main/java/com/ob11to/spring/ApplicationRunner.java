package com.ob11to.spring;

import com.ob11to.spring.database.pool.ConnectionPool;
import com.ob11to.spring.database.repository.CompanyRepository;
import com.ob11to.spring.database.repository.CrudRepository;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;


public class ApplicationRunner {

    public static void main(String[] args) {

        String value = "hello";
        System.out.println(CharSequence.class.isAssignableFrom(value.getClass()));
        System.out.println(BeanFactoryPostProcessor.class.isAssignableFrom(value.getClass()));
        System.out.println(Serializable.class.isAssignableFrom(value.getClass()));

        try (var context = new ClassPathXmlApplicationContext("application.xml")) {
            //        clazz -> String -> Map<String, Object>
            var connectionPool1 = context.getBean("pool1", ConnectionPool.class);
            System.out.println(connectionPool1);

            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}
