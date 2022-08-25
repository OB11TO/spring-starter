package com.ob11to.spring.database.repository;

import com.ob11to.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findByName(@Param("name2")String name);

    List<Company> findAllByNameContainingIgnoreCase(String fragment);
}
