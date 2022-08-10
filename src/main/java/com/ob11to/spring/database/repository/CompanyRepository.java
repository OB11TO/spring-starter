package com.ob11to.spring.database.repository;

import com.ob11to.spring.bpp.InjectBean;
import com.ob11to.spring.database.pool.ConnectionPool;

public class CompanyRepository {

    @InjectBean
    public ConnectionPool connectionPool;
}
