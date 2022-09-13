package com.ob11to.spring.http.rest;

import com.ob11to.spring.dto.ChatCreateDto;
import com.ob11to.spring.dto.ChatReadDto;
import com.ob11to.spring.service.ChatService;
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
@RequestMapping("api/v1/chats")
@RequiredArgsConstructor
public class ChatRestController {

    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<List<ChatReadDto>> findAll() {
        return ResponseEntity.ok(chatService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatReadDto> findById(@PathVariable Long id) {
        return chatService.findById(id)
                .map(chatReadDto -> ResponseEntity.ok().body(chatReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ChatReadDto> createChat(@RequestBody ChatCreateDto chatCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chatService.createChat(chatCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatReadDto> updateChat(@PathVariable Long id, @RequestBody ChatCreateDto chatCreateDto) {
        return chatService.updateChat(id, chatCreateDto)
                .map(chatReadDto -> ResponseEntity.ok().body(chatReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteChat(@PathVariable Long id) {
        if(!chatService.deleteChat(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(id);
    }
}
