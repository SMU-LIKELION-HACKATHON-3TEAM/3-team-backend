package com.grishare.controller;

import com.grishare.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/compare")
public class QuoteController {


    @GetMapping("/country")
    public BaseResponse<?> getCountry() {

    }
}
