package com.ob11to.spring.service;

import com.ob11to.spring.database.repository.ChatRepository;
import com.ob11to.spring.dto.ChatCreateDto;
import com.ob11to.spring.dto.ChatReadDto;
import com.ob11to.spring.mapper.ChatCreateMapper;
import com.ob11to.spring.mapper.ChatReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatReadMapper chatReadMapper;
    private final ChatCreateMapper chatCreateMapper;

    public List<ChatReadDto> findAll() {
        return chatRepository.findAll().stream()
                .map(chatReadMapper::map)
                .collect(toList());
    }

    public Optional<ChatReadDto> findById(Long id) {
        return chatRepository.findById(id)
                .map(chatReadMapper::map);
    }

    @Transactional
    public ChatReadDto createChat(ChatCreateDto chatCreateDto) {
        return Optional.of(chatCreateDto)
                .map(chatCreateMapper::map)
                .map(chatRepository::save)
                .map(chatReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<ChatReadDto> updateChat(Long id, ChatCreateDto chatCreateDto) {
        return chatRepository.findById(id)
                .map(entity -> chatCreateMapper.map(chatCreateDto, entity))
                .map(chatRepository::saveAndFlush)
                .map(chatReadMapper::map);
    }

    @Transactional
    public boolean deleteChat(Long id) {
        return chatRepository.findById(id)
                .map(entity -> {
                    chatRepository.delete(entity);
                    chatRepository.flush();
                    return true;
                })
                .orElse(false);
    }

}
