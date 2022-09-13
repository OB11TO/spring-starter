package com.ob11to.spring.integration.service;

import com.ob11to.spring.IntegrationTestBase;
import com.ob11to.spring.dto.ChatCreateDto;
import com.ob11to.spring.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
public class ChatServiceIT extends IntegrationTestBase {

    private final ChatService chatService;

    @Test
    void saveChat() {
        ChatCreateDto chat = new ChatCreateDto("gg");
        chatService.createChat(chat);

        var all = chatService.findAll();
        Assertions.assertThat(all).hasSize(4);
    }
}
