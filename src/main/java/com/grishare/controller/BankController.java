package com.grishare.controller;

import com.grishare.domain.Bank;
import com.grishare.dto.BankRequestDto;
import com.grishare.dto.BankReturnDto;
import com.grishare.service.BankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    BankServiceImpl bankService;

    @PostMapping("/exchangeRate/Bank")
    public ResponseEntity<Bank> createBank(@RequestBody BankRequestDto bankRequestDto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(bankService.save(bankRequestDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/exchangeRate/Bank/{id}")
    public ResponseEntity<BankReturnDto> updateBank(
            @PathVariable("id") long id,
            @RequestBody BankRequestDto bankRequestDto
    ) {
        try {
            ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(bankService.update(id, bankRequestDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/exchangeRate/Bank/{id}")
    public ResponseEntity<HttpStatus> deleteBank(@PathVariable("id") long id) {
        try {
            bankService.delete(id);
            ResponseEntity.noContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/exchangeRate/bank")
    public ResponseEntity<List<BankReturnDto>> getBanks(){
        List<BankReturnDto> bankReturnDtoList = bankService.findAll();
        return ResponseEntity.ok(bankReturnDtoList);
    }
}
