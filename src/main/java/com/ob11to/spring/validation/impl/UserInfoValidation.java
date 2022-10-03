package com.ob11to.spring.validation.impl;

import com.ob11to.spring.dto.UserCreateDto;
import com.ob11to.spring.validation.UserInfo;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.springframework.util.StringUtils.hasText;

@Component //можно не ставить (автоматически бин)
public class UserInfoValidation implements ConstraintValidator<UserInfo, UserCreateDto> {


    @Override
    public boolean isValid(UserCreateDto value, ConstraintValidatorContext context) {
        return hasText(value.getFirstname()) || hasText(value.getLastname());
    }
}
