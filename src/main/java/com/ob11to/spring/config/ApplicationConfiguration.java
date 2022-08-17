package com.ob11to.spring.config;

import com.ob11to.spring.database.pool.ConnectionPool;
import com.ob11to.spring.database.repository.UserRepository;
import com.ob11to.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

//@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.ob11to.spring")
//@ComponentScan(basePackages = "com.ob11to.spring",
//        useDefaultFilters = false,
//        includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Component.class),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
//                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")
//        })
public class ApplicationConfiguration {

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${db.username}") String username,
                                @Value("${db.pool.size}") Integer poolSize) {
        return new ConnectionPool(username, poolSize);
    }

    @Bean
    @Profile("web|prod")
//  ! & |
    public UserRepository userRepository2(ConnectionPool pool2){
        return new UserRepository(pool2);
    }

}
