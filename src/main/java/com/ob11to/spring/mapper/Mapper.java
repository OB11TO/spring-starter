package com.ob11to.spring.mapper;

public interface Mapper<F,T> {

    T map(F object);
}
