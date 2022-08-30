package com.ob11to.spring.database.repository;

import com.ob11to.spring.database.entity.User;
import com.ob11to.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);
}
