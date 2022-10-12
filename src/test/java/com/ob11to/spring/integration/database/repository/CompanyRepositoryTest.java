package com.ob11to.spring.integration.database.repository;

import com.ob11to.spring.IntegrationTestBase;
import com.ob11to.spring.database.entity.Company;
import com.ob11to.spring.database.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RequiredArgsConstructor
class CompanyRepositoryTest extends IntegrationTestBase {

    private final static Integer APPLE_ID = 9;
    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    void queryTest() {
        var google = companyRepository.findByName("Google");
        assertTrue(google.isPresent());
        google.ifPresent(System.out::println);

    }

    @Test
    void partTreeJpaQueryTest() {
        var google = companyRepository.findByName("Google");
        assertTrue(google.isPresent());
        google.ifPresent(System.out::println);

        var companyRepositoryAllByNameContainingIgnoreCase =
                companyRepository.findAllByNameContainingIgnoreCase("a");
        companyRepositoryAllByNameContainingIgnoreCase.toString();
    }


    @Test
    @Disabled
    void delete() {
        var maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

    @Test
    void findById() {
        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        Assertions.assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void save() {
        var company = Company.builder()
                .name("Apple2")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple description"
                ))
                .build();

        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}