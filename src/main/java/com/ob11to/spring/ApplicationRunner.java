package com.ob11to.spring;

import com.ob11to.spring.database.pool.ConnectionPool;
import com.ob11to.spring.database.repository.CompanyRepository;
import com.ob11to.spring.database.repository.UserRepository;
import com.ob11to.spring.ioc.Container;
import com.ob11to.spring.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ApplicationRunner {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
//        clazz -> String -> Map<String, Object>
        var connectionPool1 = context.getBean("p1",ConnectionPool.class);
        System.out.println(connectionPool1);
    }
}
