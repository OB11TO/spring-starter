package com.ob11to.spring.database.repository;

import com.ob11to.spring.bpp.Auditing;
import com.ob11to.spring.bpp.InjectBean;
import com.ob11to.spring.bpp.Transaction;
import com.ob11to.spring.database.entity.Company;
import com.ob11to.spring.database.pool.ConnectionPool;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company>{

    @InjectBean
    public ConnectionPool connectionPool;

    @PostConstruct
    private void init(){
        System.out.println("init CompanyRepository method...");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method...");
    }
}
