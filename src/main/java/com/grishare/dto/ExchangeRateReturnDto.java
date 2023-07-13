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

    private String contryName;

    private Float exchangeRate;

    private String curUnit;

    public ExchangeRateReturnDto(ExchangeRate ex){

        this.contryName = ex.getContryName();
        this.exchangeRate = ex.getExchangeRate();
        this.curUnit = ex.getCurUnit();
    }
}
