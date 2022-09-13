package com.ob11to.spring.http.rest;

import com.ob11to.spring.dto.UserChatCreateDto;
import com.ob11to.spring.dto.UserChatReadDto;
import com.ob11to.spring.service.UserChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/users_chats")
@RequiredArgsConstructor
public class UserChatRestController {

    private final UserChatService userChatService;

    @GetMapping
    public ResponseEntity<List<UserChatReadDto>> findAll() {
        return ResponseEntity.ok(userChatService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserChatReadDto> findById(@PathVariable Long id) {
        return userChatService.findById(id)
                .map(userChatReadDto -> ResponseEntity.ok().body(userChatReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserChatReadDto> saveUserChat(@RequestBody UserChatCreateDto userChatCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userChatService.saveUserChat(userChatCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserChatReadDto> updateUserChat(@PathVariable Long id, @RequestBody UserChatCreateDto userChatCreateDto) {
        return userChatService.updateUserChat(id, userChatCreateDto)
                .map(userChatReadDto -> ResponseEntity.ok().body(userChatReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUserChat(@PathVariable Long id) {
        if(!userChatService.deleteUserChat(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(id);
    }

}
