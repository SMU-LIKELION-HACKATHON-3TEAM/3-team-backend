package com.grishare.service;

import com.grishare.domain.ExchangeRate;
import com.grishare.dto.ExchangeRateRequestDto;
import com.grishare.dto.ExchangeRateReturnDto;

public interface ExchangeRateService {

    public ExchangeRate save(ExchangeRateRequestDto exRequestDto);
    public ExchangeRateReturnDto update(Long id, ExchangeRateRequestDto exRequestDto);

    public ExchangeRateReturnDto findById(Long id);
    public ExchangeRateReturnDto findByExs(Long nId, Long bId);

    public void delete(Long id);

}
