package com.ob11to.spring.integration.database.repository;

import com.ob11to.spring.IntegrationTestBase;
import com.ob11to.spring.database.entity.Role;
import com.ob11to.spring.database.entity.User;
import com.ob11to.spring.database.repository.UserRepository;
import com.ob11to.spring.dto.UserFilter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RequiredArgsConstructor
class UserRepositoryTest extends IntegrationTestBase {

    private final UserRepository userRepository;

    @Test
    void checkBatchNamed() {
        var users = userRepository.findAll();
        var resultInts = userRepository.updateCompanyAndRoleNamed(users);
        assertThat(resultInts).hasSize(5);
    }

    @Test
    void checkBatch() {
        var users = userRepository.findAll();
        var resultInts = userRepository.updateCompanyAndRole(users);
        assertThat(resultInts).hasSize(5);
    }

    @Test
    void checkJdbcStarter() {
        var users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        assertThat(users).hasSize(1);
    }

    @Test
    void checkQueryDsl() {
        var filter = new UserFilter(
                null, "ov", LocalDate.now()
        );
        var users = userRepository.findAllByFilter(filter);
        assertThat(users).hasSize(4);
    }

    @Test
    void checkAuditing() {
        var user = userRepository.findById(1L).get();
        user.setBirthDate(user.getBirthDate().plusYears(1L));
        userRepository.flush();
        System.out.println();
    }

    @Disabled("уже не нужный функционал")
    @Test
    void checkCustomImplementation() {
        var filter = new UserFilter(
                null, "%ov%", LocalDate.now()
        );
        var users = userRepository.findAllByFilter(filter);
        assertThat(users).hasSize(4);
    }

    @Test
    void checkProjection() {
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
    }

    @Test
    void checkEntityGraph() {
        var maybeUser = userRepository.findById(1L);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));

        maybeUser.ifPresent(user -> System.out.println(user.getCompany().getLocales()));
    }

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