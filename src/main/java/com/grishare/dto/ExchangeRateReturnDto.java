package com.grishare.dto;

import com.grishare.domain.ExchangeRate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateReturnDto {

    private float exchangeRate;

    public ExchangeRateReturnDto(ExchangeRate ex){
        this.exchangeRate = ex.getExchangeRate();
    }
}
