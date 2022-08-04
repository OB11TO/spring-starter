package com.ob11to.spring.service;

import com.ob11to.spring.database.repository.CompanyRepository;
import com.ob11to.spring.database.repository.UserRepository;

public class UserService {

    public final UserRepository userRepository;
    public final CompanyRepository companyRepository;

    public UserService(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
}
