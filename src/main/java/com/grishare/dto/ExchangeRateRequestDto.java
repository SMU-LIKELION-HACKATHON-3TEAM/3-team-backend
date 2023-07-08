package com.grishare.dto;

import com.grishare.domain.Bank;
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

    private String bank;

    private float exchangeRate;

    private String curUnit;

    public ExchangeRate toEntity(){
        ExchangeRate ex = new ExchangeRate(
                this.nationId,
                Bank.valueOf(this.bank),
                this.curUnit,
                this.exchangeRate
        );
        return ex;
    }
}
