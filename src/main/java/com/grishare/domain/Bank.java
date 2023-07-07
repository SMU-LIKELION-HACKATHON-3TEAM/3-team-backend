package com.grishare.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="bank")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "bank_name")
    private String bankName;

    @OneToMany
    @JoinColumn(name="bank_id")
    private Collection<ExchangeRate> er;

    public Bank(String bankName) {
        this.bankName = bankName;
    }
}
