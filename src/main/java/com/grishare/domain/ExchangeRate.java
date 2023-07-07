package com.grishare.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="exchangerate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nation_id")
    private Long nationId;

    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "exchange_Rate")
    private float exchangeRate;

    public ExchangeRate(Long nationId, Long bankId, float exchangeRate) {
        this.nationId = nationId;
        this.bankId = bankId;
        this.exchangeRate = exchangeRate;
    }
}
