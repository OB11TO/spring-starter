package com.ob11to.spring.service;

import com.ob11to.spring.database.entity.Company;
import com.ob11to.spring.database.repository.CrudRepository;
import com.ob11to.spring.database.repository.UserRepository;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
public class UserService {

    public final UserRepository userRepository;
    public final CrudRepository<Integer, Company> companyRepository;

    public UserService(UserRepository userRepository,
                       CrudRepository<Integer,Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
}
