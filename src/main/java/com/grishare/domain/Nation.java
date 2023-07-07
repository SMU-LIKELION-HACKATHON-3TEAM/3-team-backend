package com.grishare.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

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
    private String ISO_3166_1_alpha_2;

    @Column(name = "ISO_3166_1_alpha_3")
    private String ISO_3166_1_alpha_3;

    @Column(name = "ISO_3166_1_numeric")
    private int ISO_3166_1_numeric;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_en_name")
    private String countryEnName;

    @Column(name = "continent_code")
    private String continentCode;

    @Column(name = "travel_warning")
    private int travelWarning;

    @Column(name = "warning_history")
    private String warningHistory;

    @OneToMany
    @JoinColumn(name="nation_id")
    private Collection<ExchangeRate> er;

    public Nation(String ISO_3166_1_alpha_2, String ISO_3166_1_alpha_3, int ISO_3166_1_numeric, String countryName, String countryEnName, String continentCode, int travelWarning, String warningHistory) {
        this.ISO_3166_1_alpha_2 = ISO_3166_1_alpha_2;
        this.ISO_3166_1_alpha_3 = ISO_3166_1_alpha_3;
        this.ISO_3166_1_numeric = ISO_3166_1_numeric;
        this.countryName = countryName;
        this.countryEnName = countryEnName;
        this.continentCode = continentCode;
        this.travelWarning = travelWarning;
        this.warningHistory = warningHistory;
    }

}