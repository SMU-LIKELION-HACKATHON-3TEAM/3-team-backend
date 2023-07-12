package com.grishare.controller;

import com.grishare.base.BaseResponse;
import com.grishare.dto.QuoteADReturnDto;
import com.grishare.service.QuoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/compare")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping("/nation")
    public BaseResponse<?> getCountry() {
        return quoteService.getCountry();
    }

    @GetMapping("division/{nationId}")
    public BaseResponse<?> getDivision(@PathVariable("nationId") long nationId) {
        return quoteService.getDivision(nationId);
    }
}
