package com.ob11to.spring.database.pool;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

public class ConnectionPool implements InitializingBean {

    private String username;
    private Integer poolSize;
    private List<Object> args;
    private Map<String, Object> properties;

    public ConnectionPool(String username,
                          Integer poolSize,
                          List<Object> args,
                          Map<String, Object> properties) {
        this.username = username;
        this.poolSize = poolSize;
        this.args = args;
        this.properties = properties;
    }

    public ConnectionPool(){

    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Вызовется вторым, после @PostConstruct");
    }

    @PostConstruct
    private void init() {
        System.out.println("Вызовется третьим");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Clean connection pool");
    }
}
