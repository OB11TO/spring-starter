package com.ob11to.spring.mapper;

import com.ob11to.spring.database.entity.Chat;
import com.ob11to.spring.database.entity.User;
import com.ob11to.spring.database.entity.UserChat;
import com.ob11to.spring.database.repository.ChatRepository;
import com.ob11to.spring.database.repository.UserRepository;
import com.ob11to.spring.dto.UserChatCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserChatCreateMapper implements Mapper<UserChatCreateDto, UserChat> {

    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    @Override
    public UserChat map(UserChatCreateDto object) {
        UserChat usrChat = new UserChat();
        copy(object, usrChat);
        return usrChat;
    }

    private void copy(UserChatCreateDto fromObject, UserChat toObject) {
        toObject.setUser(getUser(fromObject.getUserId()));
        toObject.setChat(getChat(fromObject.getChatId()));
    }

    public User getUser(Long userId) {
        return Optional.ofNullable(userId)
                .flatMap(userRepository::findById)
                .orElse(null);
    }

    public Chat getChat(Long chatId) {
        return Optional.ofNullable(chatId)
                .flatMap(chatRepository::findById)
                .orElse(null);
    }

    @Override
    public UserChat map(UserChatCreateDto fromObject, UserChat toObject) {
        copy(fromObject, toObject);

        return toObject;
    }
}
