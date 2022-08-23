package com.ob11to.spring.integration.database.repository;

import com.ob11to.spring.database.entity.Company;
import com.ob11to.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@IT
@RequiredArgsConstructor
@Transactional
class CompanyRepositoryTest {

    private final EntityManager entityManager;

    @Test
    void findById() {
        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        Assertions.assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void save(){
        var company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple description"
                ))
                .build();

        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}