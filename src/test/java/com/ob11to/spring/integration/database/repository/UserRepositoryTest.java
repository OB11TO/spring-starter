package com.ob11to.spring.integration.database.repository;

import com.ob11to.spring.database.entity.Role;
import com.ob11to.spring.database.entity.User;
import com.ob11to.spring.database.repository.UserRepository;
import com.ob11to.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
@Transactional
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void checkPageable() {
        var pageable = PageRequest.of(1, 2, Sort.by("id"));
        var users = userRepository.findAllBy(pageable);
        assertThat(users).hasSize(2);
    }

    @Test
    void checkSort() {
        var sort = Sort.sort(User.class);
        var sortBy = sort.by(User::getFirstname)
                .and(sort.by(User::getLastname));

        var allUsers = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortBy);
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkFirstTop() {
        var topUser = userRepository.findFirstByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkUpdate() {
        var ivan = userRepository.getReferenceById(1L);
        assertSame(Role.ADMIN, ivan.getRole());
        ivan.setBirthDate(LocalDate.now());

        var updateRoleCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, updateRoleCount);

        var theSameIvan = userRepository.getReferenceById(1L);
        assertSame(Role.USER, theSameIvan.getRole());
    }

    @Test
    void checkQueries() {
        var users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
        users.forEach(System.out::println);

        var ivan = userRepository.findAllByUsername("ivan@gmail.com");
        for (User user : ivan) {
            System.out.println(user.getFirstname());
            System.out.println(user.getCompany());
        }
    }
}