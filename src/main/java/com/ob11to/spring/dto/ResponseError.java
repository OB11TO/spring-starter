package com.ob11to.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseError {

    private int code;
    private String msg;
}