package com.grishare.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="nation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ISO_3166_1_alpha_2")
    private String iso2;

    @Column(name = "ISO_3166_1_alpha_3")
    private String iso3;

    @Column(name = "ISO_3166_1_numeric")
    private int isoN;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_en_name")
    private String countryEnName;

    @Column(name = "continent_code")
    private String continentCode;

    @Column(name = "travel_warning")
    private Integer travelWarning;

    @Column(name = "warning_history")
    private String warningHistory;

    @Column(name = "currency")
    private String currency;

    @OneToMany
    @JoinColumn(name="nation_id")
    private Collection<ExchangeRate> er;

    public Nation(String iso2, String iso3, int isoN, String countryName, String countryEnName, String continentCode, Integer travelWarning, String warningHistory, String currency) {
        this.iso2 = iso2;
        this.iso3 = iso3;
        this.isoN = isoN;
        this.countryName = countryName;
        this.countryEnName = countryEnName;
        this.continentCode = continentCode;
        this.travelWarning = travelWarning;
        this.warningHistory = warningHistory;
        this.currency = currency;
    }

}