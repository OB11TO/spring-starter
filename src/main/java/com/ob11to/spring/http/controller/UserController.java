package com.ob11to.spring.http.controller;

import com.ob11to.spring.dto.UserCreateDto;
import com.ob11to.spring.dto.UserReadDto;
import com.ob11to.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserReadDto>> findAll(Model model) {
//        model.addAttribute("users", userService.findAll());
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.findById());
        return "user/user";
    }

    @PostMapping
    public String createUser(UserCreateDto userCreateDto) {
//        userService.createUser(userCreateDto);
        return "redirect:/users/" + 25;
    }

    //    @PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id, UserCreateDto userCreateDto) {
//        userService.update(id, userCreateDto)
        return "redirect:/users/" + 25;
    }

    //    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
//        userService.delete(id);
        return "redirect:/users";
    }

}
