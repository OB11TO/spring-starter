package com.ob11to.spring.mapper;

import com.ob11to.spring.database.entity.User;
import com.ob11to.spring.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto map(User user) {
        var company = Optional.ofNullable(user.getCompany())
                .map(companyReadMapper::map)
                .orElse(null);
        return new UserReadDto(
                user.getId(),
                user.getUsername(),
                user.getBirthDate(),
                user.getFirstname(),
                user.getLastname(),
                user.getRole(),
                company
        );
    }
}
