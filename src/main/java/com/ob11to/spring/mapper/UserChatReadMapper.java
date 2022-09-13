package com.ob11to.spring.mapper;

import com.ob11to.spring.database.entity.UserChat;
import com.ob11to.spring.dto.UserChatReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserChatReadMapper implements Mapper<UserChat, UserChatReadDto> {

    private final UserReadMapper userReadMapper;
    private final ChatReadMapper chatReadMapper;

    @Override
    public UserChatReadDto map(UserChat object) {
        var userReadDto = Optional.ofNullable(object.getUser())
                .map(userReadMapper::map)
                .orElseThrow();
        var chatReadDto = Optional.ofNullable(object.getChat())
                .map(chatReadMapper::map)
                .orElseThrow();

        return new UserChatReadDto(
                object.getId(),
                userReadDto,
                chatReadDto
        );
    }
}
