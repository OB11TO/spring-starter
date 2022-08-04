package com.ob11to.spring.database.repository;

import com.ob11to.spring.database.pool.ConnectionPool;

public class CompanyRepository {

    public final ConnectionPool connectionPool;

    public CompanyRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
