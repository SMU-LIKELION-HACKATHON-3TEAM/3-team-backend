package com.grishare.controller;

import com.grishare.domain.ExchangeRate;
import com.grishare.dto.ExchangeRateRequestDto;
import com.grishare.dto.ExchangeRateReturnDto;
import com.grishare.service.ExchangeRateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ExchangeRateController {

    @Autowired
    ExchangeRateServiceImpl exService;

    @PostMapping("/exchangeRate/ExchangeRate")
    public ResponseEntity<ExchangeRate> createExchangeRate(@RequestBody ExchangeRateRequestDto exRequestDto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(exService.save(exRequestDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/exchangeRate/ExchangeRate/{id}")
    public ResponseEntity<ExchangeRateReturnDto> updateExchangeRate(
            @PathVariable("id") long id,
            @RequestBody ExchangeRateRequestDto exRequestDto
    ) {
        try {
            ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(exService.update(id, exRequestDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/exchangeRate/ExchangeRate/{id}")
    public ResponseEntity<HttpStatus> deleteExchangeRate(@PathVariable("id") long id) {
        try {
            exService.delete(id);
            ResponseEntity.noContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/exchangeRate/{nId}/{bId}")
    public ResponseEntity<ExchangeRateReturnDto> getExchangeRate(@PathVariable("nId") long nId, @PathVariable("bId") long bId){
        ExchangeRateReturnDto exReturnDto = exService.findByExs(nId, bId);
        return ResponseEntity.ok(exReturnDto);
    }
}
