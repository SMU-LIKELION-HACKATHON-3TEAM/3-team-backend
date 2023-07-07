package com.grishare.service;

import com.grishare.domain.ExchangeRate;
import com.grishare.dto.ExchangeRateRequestDto;
import com.grishare.dto.ExchangeRateReturnDto;
import com.grishare.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{

    @Autowired
    private ExchangeRateRepository exRepository;

    @Override
    public ExchangeRate save(ExchangeRateRequestDto exRequestDto) {
        try{
            return  exRepository
                    .save(
                            exRequestDto.toEntity()
                    );
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ExchangeRateReturnDto update(Long id, ExchangeRateRequestDto exRequestDto) {
        try{
            Optional<ExchangeRate> exData = exRepository.findById(id);
            if(exData.isPresent()){
                ExchangeRate _ex = exData.get();
                _ex.setNationId(exRequestDto.getNationId());
                _ex.setBankId(exRequestDto.getBankId());
                _ex.setExchangeRate(exRequestDto.getExchangeRate());
                return  new ExchangeRateReturnDto(_ex);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ExchangeRateReturnDto findById(Long id) {
        try{
            Optional<ExchangeRate> exData = exRepository.findById(id);
            if(exData.isPresent()){
                return  new ExchangeRateReturnDto(exData.get());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ExchangeRateReturnDto findByExs(Long nId, Long bId) {
        ExchangeRate ex = exRepository.findByExs(nId, bId);
        return new ExchangeRateReturnDto(ex);
    }

    @Override
    public void delete(Long id) {
        try{
            exRepository.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
