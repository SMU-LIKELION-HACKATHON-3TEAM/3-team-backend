package com.grishare.service;

import com.grishare.domain.Bank;
import com.grishare.dto.BankRequestDto;
import com.grishare.dto.BankReturnDto;

import java.util.List;

public interface BankService {

    public Bank save(BankRequestDto bankRequestDto);
    public BankReturnDto update(Long id, BankRequestDto bankRequestDto);

    public BankReturnDto findById(Long id);
    public List<BankReturnDto> findAll();

    public void delete(Long id);

}
