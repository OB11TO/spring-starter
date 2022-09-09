package com.ob11to.spring.service;

import com.ob11to.spring.database.repository.CompanyRepository;
import com.ob11to.spring.dto.CompanyReadDto;
import com.ob11to.spring.listner.entity.AccessType;
import com.ob11to.spring.listner.entity.EntityEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

    public Optional<CompanyReadDto> findById(Integer id){
        return companyRepository.findById(id)
                .map(entity -> {
                    eventPublisher.publishEvent(new EntityEvent(entity, AccessType.READ));
                   return new CompanyReadDto(entity.getId(),null);
                });
    }
}
