package com.ob11to.spring.service;

import com.ob11to.spring.database.entity.Company;
import com.ob11to.spring.database.repository.CrudRepository;
import com.ob11to.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;
    public final CrudRepository<Integer, Company> companyRepository;

}
