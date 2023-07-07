package com.grishare.dto;

import com.grishare.domain.Bank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankRequestDto {

    private String bankCode;

    private String bankName;

    public Bank toEntity(){
        Bank bank = new Bank(
                this.bankName
        );
        return bank;
    }

}
