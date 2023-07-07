package com.grishare.service;

import com.grishare.domain.Bank;
import com.grishare.dto.BankRequestDto;
import com.grishare.dto.BankReturnDto;
import com.grishare.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankServiceImpl implements BankService{

    @Autowired
    private BankRepository bankRepository;

    @Override
    public Bank save(BankRequestDto bankRequestDto) {
        try{
            return  bankRepository
                    .save(
                            bankRequestDto.toEntity()
                    );
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BankReturnDto update(Long id, BankRequestDto bankRequestDto) {
        try{
            Optional<Bank> bankData = bankRepository.findById(id);
            if(bankData.isPresent()){
                Bank _bank = bankData.get();
                _bank.setBankName((bankRequestDto.getBankName()));
                return  new BankReturnDto(_bank);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BankReturnDto findById(Long id) {
        try{
            Optional<Bank> bankData = bankRepository.findById(id);
            if(bankData.isPresent()){
                return  new BankReturnDto(bankData.get());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BankReturnDto> findAll() {
        List<Bank> bankList = bankRepository.findAll();
        List<BankReturnDto> bankReturnDtoList = new ArrayList<>();
        return bankList.stream().map(BankReturnDto::new).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        try{
            bankRepository.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
