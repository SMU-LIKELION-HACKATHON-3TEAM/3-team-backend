package com.grishare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/api/sample")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<String> sampleApi(){
        return ResponseEntity.ok("반환내용");
    }
}