package com.ob11to.spring.integration.service;

import com.ob11to.spring.IntegrationTestBase;
import com.ob11to.spring.database.entity.Role;
import com.ob11to.spring.dto.UserCreateDto;
import com.ob11to.spring.dto.UserReadDto;
import com.ob11to.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;

    private final UserService userService;

    @Test
    void findAll() {
        var maybeAllUsers = userService.findAll();
        assertThat(maybeAllUsers).hasSize(5);
    }

    @Test
    void findById() {
        var maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));
    }


    @Test
    void createUser() {
        var userCreateDto = new UserCreateDto(
                "test@gmail.com",
                "{noop}123",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1
        );
        var actualResult = userService.createUser(userCreateDto);

        assertEquals(userCreateDto.getUsername(), actualResult.getUsername());
        assertEquals(userCreateDto.getBirthDate(), actualResult.getBirthDate());
        assertEquals(userCreateDto.getFirstname(), actualResult.getFirstname());
        assertEquals(userCreateDto.getLastname(), actualResult.getLastname());
        assertEquals(userCreateDto.getCompanyId(), actualResult.getCompany().getId());
        assertSame(userCreateDto.getRole(), actualResult.getRole());
    }

    @Test
    void update() {
        UserCreateDto userDto = new UserCreateDto(
                "test@gmail.com",
                "{noop}123",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1
        );

        Optional<UserReadDto> actualResult = userService.updateUser(USER_1, userDto);

        assertTrue(actualResult.isPresent());
        actualResult.ifPresent(user -> {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertEquals(userDto.getCompanyId(), user.getCompany().getId());
            assertSame(userDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
        assertFalse(userService.deleteUser(-124L));
        assertTrue(userService.deleteUser(USER_1));
    }

}
