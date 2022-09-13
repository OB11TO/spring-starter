package com.ob11to.spring.mapper;

import com.ob11to.spring.database.entity.Chat;
import com.ob11to.spring.dto.ChatReadDto;
import org.springframework.stereotype.Component;

@Component
public class ChatReadMapper implements Mapper<Chat, ChatReadDto> {

    @Override
    public ChatReadDto map(Chat object) {
        return new ChatReadDto(
                object.getId(),
                object.getName()
        );
    }
}
