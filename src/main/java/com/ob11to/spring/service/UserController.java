package com.ob11to.spring.service;

import com.ob11to.spring.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
//        model.addAttribute("users", userService.findAll());
        return "user/users";
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
