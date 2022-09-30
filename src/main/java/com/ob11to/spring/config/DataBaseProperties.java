package com.ob11to.spring.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "db")
@Validated
public class DataBaseProperties {
    String username;
    String password;
    String driver;
    String url;
    String hosts;
    PoolProperties pool;
    List<PoolProperties> pools;
    Map<String, Object> properties;


    @Value
    public static class PoolProperties{
        Integer size;
        Integer timeout;
    }
}
