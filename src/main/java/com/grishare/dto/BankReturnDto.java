package com.grishare.dto;

import com.grishare.domain.Bank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankReturnDto {
    private Long id;
    private String bankname;

    public BankReturnDto(Bank bank){
        this.id = bank.getId();
        this.bankname = bank.getBankName();
    }
}
