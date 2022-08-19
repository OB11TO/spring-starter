package com.ob11to.spring.database.pool;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Getter
@Component("pool1")
@RequiredArgsConstructor
public class ConnectionPool {

    @Value("${db.username}")
    private final String username;
    @Value("${db.pool.size}")
    private final Integer poolSize;


    @PostConstruct
    private void init() {
        System.out.println("Вызовется первым");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Clean connection pool");
    }
}
