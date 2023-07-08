package com.grishare.service;

import com.grishare.domain.ExchangeRate;
import com.grishare.dto.ExchangeRateRequestDto;
import com.grishare.dto.ExchangeRateReturnDto;

import java.io.IOException;

public interface ExchangeRateService {

    public ExchangeRate save(ExchangeRateRequestDto exRequestDto);
    public void update() throws IOException;

    public ExchangeRateReturnDto findById(Long id);
    public ExchangeRateReturnDto findByNationIdAndBank(Long nationId, String bank);

    public void delete(Long id);

}
