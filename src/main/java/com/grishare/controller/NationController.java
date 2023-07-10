package com.grishare.controller;

import com.grishare.dto.NationReturnDto;
import com.grishare.service.NationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class NationController {

    @Autowired
    NationServiceImpl nationService;

    @GetMapping("/exchangeRate/country")
    public ResponseEntity<List<NationReturnDto>> getNations(){
        List<NationReturnDto> nationReturnDtoList = nationService.findAll();
        return ResponseEntity.ok(nationReturnDtoList);
    }
}
