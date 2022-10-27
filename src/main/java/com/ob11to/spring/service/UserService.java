package com.ob11to.spring.service;

import com.ob11to.spring.database.repository.UserRepository;
import com.ob11to.spring.database.repository.querydsl.QPredicates;
import com.ob11to.spring.dto.ChatReadDto;
import com.ob11to.spring.dto.UserCreateDto;
import com.ob11to.spring.dto.UserFilter;
import com.ob11to.spring.dto.UserReadDto;
import com.ob11to.spring.mapper.ChatReadMapper;
import com.ob11to.spring.mapper.UserCreateMapper;
import com.ob11to.spring.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.ob11to.spring.database.entity.QUser.user;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;
    private final ChatReadMapper chatReadMapper;

    public List<ChatReadDto> findAllByUserChats(Long id) {
        return userRepository.findAllByUserChats(id).stream()
                .map(chatReadMapper::map)
                .collect(toList());
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .collect(toList());
    }

    public List<UserReadDto> findAllByFilter(UserFilter userFilter) {
        return userRepository.findAllByFilter(userFilter).stream()
                .map(userReadMapper::map)
                .collect(toList());
    }

//    @PostFilter("filterObject.role.name().equals('ADMIN')")
    public Page<UserReadDto> findAllByFilterAndPageable(UserFilter filter, Pageable pageable) {
        var predicate = QPredicates.builder()
                .add(filter.getFirstname(), user.firstname::containsIgnoreCase)
                .add(filter.getLastname(), user.lastname::containsIgnoreCase)
                .add(filter.getBirthDate(), user.birthDate::before)
                .build();

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::map);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto createUser(UserCreateDto userCreateDto) {
        return Optional.of(userCreateDto)
                .map(userCreateMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> updateUser(Long id, UserCreateDto userCreateDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateMapper.map(userCreateDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);

    }

    @Transactional
    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }
}
