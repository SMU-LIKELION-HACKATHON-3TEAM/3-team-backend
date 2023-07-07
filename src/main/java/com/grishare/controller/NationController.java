package com.grishare.controller;

import com.grishare.domain.Nation;
import com.grishare.dto.NationRequestDto;
import com.grishare.dto.NationReturnDto;
import com.grishare.service.NationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class NationController {

    @Autowired
    NationServiceImpl nationService;

    @PostMapping("/exchangeRate/Nation")
    public ResponseEntity<Nation> createNation(@RequestBody NationRequestDto nationRequestDto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nationService.save(nationRequestDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/exchangeRate/Nation/{id}")
    public ResponseEntity<NationReturnDto> updateNation(
            @PathVariable("id") long id,
            @RequestBody NationRequestDto nationRequestDto
    ) {
        try {
            ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(nationService.update(id, nationRequestDto));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/exchangeRate/Nation/{id}")
    public ResponseEntity<HttpStatus> deleteNation(@PathVariable("id") long id) {
        try {
            nationService.delete(id);
            ResponseEntity.noContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/exchangeRate/country")
    public ResponseEntity<List<NationReturnDto>> getNations(){
        List<NationReturnDto> nationReturnDtoList = nationService.findAll();
        return ResponseEntity.ok(nationReturnDtoList);
    }
}
