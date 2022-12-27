package com.ob11to.spring.http.rest;

import com.ob11to.spring.service.NumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/numbers")
public class NumbersRestController {

    private final NumberService numberService;

    @GetMapping("/random")
    public ResponseEntity<String> random() {
        return ResponseEntity.ok(numberService.getRandomNumber());
    }

    @GetMapping("/next")
    public ResponseEntity<String> next() {
        return ResponseEntity.ok(numberService.getNextNumber());
    }

}
