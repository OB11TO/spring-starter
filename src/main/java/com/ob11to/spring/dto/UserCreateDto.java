package com.ob11to.spring.dto;

import com.ob11to.spring.database.entity.Role;
import com.ob11to.spring.validation.UserInfo;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo
public class UserCreateDto {
    @Email
    String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthDate;

    @NotEmpty
    @Size(min = 3, max = 64)
    String firstname;

    @NotEmpty
    @NotBlank
    String lastname;

    Role role;

    Integer companyId;


}
