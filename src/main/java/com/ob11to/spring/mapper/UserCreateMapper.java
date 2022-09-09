package com.ob11to.spring.mapper;

import com.ob11to.spring.database.entity.Company;
import com.ob11to.spring.database.entity.User;
import com.ob11to.spring.database.repository.CompanyRepository;
import com.ob11to.spring.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {

    private final CompanyRepository companyRepository;

    @Override
    public User map(UserCreateDto object) {
       return User.builder()
               .username(object.getUsername())
               .birthDate(object.getBirthDate())
               .firstname(object.getFirstname())
               .lastname(object.getLastname())
               .role(object.getRole())
               .company(getCompany(object.getCompanyId()))
               .build();
    }

    public Company getCompany(Integer companyId) {
        return Optional.ofNullable(companyId)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }
}
