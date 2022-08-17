package com.ob11to.spring.service;

import com.ob11to.spring.database.entity.Company;
import com.ob11to.spring.database.repository.CrudRepository;
import com.ob11to.spring.dto.CompanyReadDto;
import com.ob11to.spring.listner.entity.AccessType;
import com.ob11to.spring.listner.entity.EntityEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final CrudRepository<Integer, Company> companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public CompanyService(CrudRepository<Integer, Company> companyRepository,
                          UserService userService,
                          ApplicationEventPublisher eventPublisher) {
        this.companyRepository = companyRepository;
        this.userService = userService;
        this.eventPublisher = eventPublisher;
    }

    public Optional<CompanyReadDto> findById(Integer id){
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                   return new CompanyReadDto(entity.getId());
                });
    }
}
