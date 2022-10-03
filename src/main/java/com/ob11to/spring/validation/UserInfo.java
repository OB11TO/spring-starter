package com.ob11to.spring.validation;

import com.ob11to.spring.validation.impl.UserInfoValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {UserInfoValidation.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface UserInfo {

    String message() default "{Firstname or lastname should be filled in}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
