package com.ob11to.spring.service;

import com.ob11to.spring.database.entity.Company;
import com.ob11to.spring.database.repository.CrudRepository;
import com.ob11to.spring.dto.CompanyReadDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final CrudRepository<Integer, Company> companyRepository;
    private final UserService userService;

    public CompanyService(CrudRepository<Integer, Company> companyRepository,
                          UserService userService) {
        this.companyRepository = companyRepository;
        this.userService = userService;
    }

    public Optional<CompanyReadDto> findById(Integer id){
        return companyRepository.findById(id)
                .map(entity -> new CompanyReadDto(entity.getId()));
    }
}
