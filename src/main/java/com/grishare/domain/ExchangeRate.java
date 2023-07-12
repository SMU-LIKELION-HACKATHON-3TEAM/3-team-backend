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

    @Column(name = "contry_name")
    private String contryName;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank")
    private Bank bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nation_id")
    private Nation nation;

    @Column(name = "contry_code")
    private String contryCode;

    @Column(name = "cur_unit")
    private String curUnit;

    @Column(name = "exchange_rate")
    private Float exchangeRate;

    public ExchangeRate(String contryName, Bank bank, String contryCode, String curUnit, Float exchangeRate) {
        this.contryName = contryName;
        this.bank = bank;
        this.contryCode = contryCode;
        this.curUnit = curUnit;
        this.exchangeRate = exchangeRate;
    }
}
