package com.ob11to.spring.http.rest;

import com.ob11to.spring.service.XmlUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class XmlUserRestController {

    private final XmlUserService xmlUserService;

    @GetMapping
    public ResponseEntity<byte[]> generateWorkReport() {
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=Report.xlsx");
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(xmlUserService.generateWorkReport());
    }
}
