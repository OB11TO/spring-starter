package com.ob11to.spring.database.repository;

import com.ob11to.spring.database.pool.ConnectionPool;

public class UserRepository {

    public final ConnectionPool connectionPool;

    public UserRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
