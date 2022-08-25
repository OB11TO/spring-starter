package com.ob11to.spring.config;

import com.ob11to.spring.database.pool.ConnectionPool;
import com.ob11to.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

//@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
@Configuration
public class ApplicationConfiguration {

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${db.username}") String username,
                                @Value("${db.pool.size}") Integer poolSize) {
        return new ConnectionPool(username, poolSize);
    }

}
