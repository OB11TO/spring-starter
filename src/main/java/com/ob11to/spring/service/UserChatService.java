package com.ob11to.spring.service;

import com.ob11to.spring.database.repository.UserChatRepository;
import com.ob11to.spring.dto.UserChatCreateDto;
import com.ob11to.spring.dto.UserChatReadDto;
import com.ob11to.spring.mapper.UserChatCreateMapper;
import com.ob11to.spring.mapper.UserChatReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserChatService {

    private final UserChatRepository userChatRepository;
    private final UserChatReadMapper userChatReadMapper;
    private final UserChatCreateMapper userChatCreateMapper;

    public List<UserChatReadDto> findAll() {
        return userChatRepository.findAll().stream()
                .map(userChatReadMapper::map)
                .collect(toList());
    }

    public Optional<UserChatReadDto> findById(Long id) {
        return userChatRepository.findById(id)
                .map(userChatReadMapper::map);
    }

    @Transactional
    public UserChatReadDto saveUserChat(UserChatCreateDto userChatCreateDto) {
        return Optional.of(userChatCreateDto)
                .map(userChatCreateMapper::map)
                .map(userChatRepository::save)
                .map(userChatReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserChatReadDto> updateUserChat(Long id, UserChatCreateDto userChatCreateDto) {
        return userChatRepository.findById(id)
                .map(entity -> userChatCreateMapper.map(userChatCreateDto, entity))
                .map(userChatRepository::saveAndFlush)
                .map(userChatReadMapper::map);
    }

    @Transactional
    public boolean deleteUserChat(Long id) {
        return userChatRepository.findById(id)
                .map(entity -> {
                    userChatRepository.delete(entity);
                    userChatRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
