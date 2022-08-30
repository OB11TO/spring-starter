package com.ob11to.spring.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class UserFilter {
    String firstname;
    String lastname;
    LocalDate birthDate;
}
