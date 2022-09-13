package com.ob11to.spring.mapper;

import com.ob11to.spring.database.entity.Chat;
import com.ob11to.spring.dto.ChatCreateDto;
import org.springframework.stereotype.Component;

@Component
public class ChatCreateMapper implements Mapper<ChatCreateDto, Chat> {

    @Override
    public Chat map(ChatCreateDto object) {
        Chat chat = new Chat();
        copy(object, chat);

        return chat;
    }

    private void copy(ChatCreateDto fromObject, Chat toObject) {
        toObject.setName(fromObject.getName());
    }

    @Override
    public Chat map(ChatCreateDto fromObject, Chat toObject) {
        copy(fromObject, toObject);

        return toObject;
    }
}
