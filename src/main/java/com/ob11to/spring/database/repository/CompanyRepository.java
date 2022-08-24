package com.ob11to.spring.database.repository;

import com.ob11to.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findById(Integer id);

    void delete(Company entity);
}
