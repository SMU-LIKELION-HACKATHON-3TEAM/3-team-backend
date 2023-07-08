package com.grishare.controller;

import com.grishare.base.BaseResponse;
import com.grishare.service.QuoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/compare")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/country")
    public BaseResponse<?> getCountry() {
        return quoteService.getCountry();
    }
}
