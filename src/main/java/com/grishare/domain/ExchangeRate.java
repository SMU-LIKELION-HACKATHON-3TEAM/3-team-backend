package com.grishare.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="exchangerate")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nation_id")
    private Long nationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank")
    private Bank bank;

    @Column(name = "cur_unit")
    private String curUnit;

    @Column(name = "exchange_rate")
    private float exchangeRate;

    public ExchangeRate(Long nationId, Bank bank, String curUnit, float exchangeRate) {
        this.nationId = nationId;
        this.bank = bank;
        this.curUnit = curUnit;
        this.exchangeRate = exchangeRate;
    }
}
