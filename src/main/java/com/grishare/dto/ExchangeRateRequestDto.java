package com.grishare.dto;

import com.grishare.domain.ExchangeRate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateRequestDto {

    private Long nationId;

    private Long bankId;

    private float exchangeRate;

    public ExchangeRate toEntity(){
        ExchangeRate ex = new ExchangeRate(
                this.nationId,
                this.bankId,
                this.exchangeRate
        );
        return ex;
    }
}
