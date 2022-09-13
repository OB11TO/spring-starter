package com.ob11to.spring.dto;

import lombok.Value;

@Value
public class UserChatReadDto {
    Long id;
    UserReadDto user;
    ChatReadDto chat;

}
