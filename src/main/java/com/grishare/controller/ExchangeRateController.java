package com.grishare.controller;

import com.grishare.dto.ExchangeRateReturnDto;
import com.grishare.service.ExchangeRateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExchangeRateController {

    @Autowired
    ExchangeRateServiceImpl exService;

    @GetMapping("/exchangeRate/{contryName}/{bank}")
    public ResponseEntity<ExchangeRateReturnDto> getExchangeRate(@PathVariable("contryName") String contryName, @PathVariable("bank") String bank){
        ExchangeRateReturnDto exReturnDto = exService.findByContryNameAndBank(contryName, bank);
        return ResponseEntity.ok(exReturnDto);
    }

    @GetMapping("/exchangeRate")
    public ResponseEntity<List<ExchangeRateReturnDto>> getExchangeRates(){
        List<ExchangeRateReturnDto> exReturnDtoList = exService.findAll();
        return ResponseEntity.ok(exReturnDtoList);
    }
}
