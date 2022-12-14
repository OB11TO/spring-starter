package com.ob11to.spring.mapper;

import com.ob11to.spring.database.entity.Company;
import com.ob11to.spring.database.entity.User;
import com.ob11to.spring.database.repository.CompanyRepository;
import com.ob11to.spring.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateDto fromObject, User toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    @Override
    public User map(UserCreateDto object) {
       User user = new User();
       copy(object, user);

       return user;
    }

    private void copy(UserCreateDto object, User user) {
        user.setUsername(object.getUsername());
        user.setFirstname(object.getFirstname());
        user.setLastname(object.getLastname());
        user.setBirthDate(object.getBirthDate());
        user.setRole(object.getRole());
        user.setCompany(getCompany(object.getCompanyId()));

        Optional.ofNullable(object.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);
    }

    public Company getCompany(Integer companyId) {
        return Optional.ofNullable(companyId)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }
}
