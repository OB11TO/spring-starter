package com.ob11to.spring.http.rest;

import com.ob11to.spring.dto.ChatReadDto;
import com.ob11to.spring.dto.UserCreateDto;
import com.ob11to.spring.dto.UserReadDto;
import com.ob11to.spring.service.UserService;
import com.ob11to.spring.validation.group.UpdateAction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.groups.Default;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@javax.annotation.Generated(value = "org.springdoc.demo.app2.codegen.languages.SpringCodegen", date = "2019-07-11T00:09:29.839+02:00[Europe/Paris]")
public class UserRestController {

    private final UserService userService;

    @GetMapping("/{id}/chats")
    public ResponseEntity<List<ChatReadDto>> findAllByUserChats(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findAllByUserChats(id));
    }

    @GetMapping
    public ResponseEntity<List<UserReadDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReadDto> findById(@PathVariable Long id) {
        return userService.findById(id)
                .map(userReadDto -> ResponseEntity.ok().body(userReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
 
    @PostMapping
    public ResponseEntity<UserReadDto> createUser(@RequestBody @Validated UserCreateDto userCreateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserReadDto> updateUser(@PathVariable Long id,
                                                  @Validated({Default.class, UpdateAction.class})
                                                  @RequestBody UserCreateDto userCreateDto) {
        return userService.updateUser(id, userCreateDto)
                .map(userReadDto -> ResponseEntity.ok().body(userReadDto))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        if(!userService.deleteUser(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(id);
    }
}
