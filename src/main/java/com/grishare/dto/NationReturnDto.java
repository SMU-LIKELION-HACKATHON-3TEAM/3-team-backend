package com.grishare.dto;

import com.grishare.domain.Nation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NationReturnDto {

    private Long id;

    private String ISO_3166_1_alpha_2;

    private String ISO_3166_1_alpha_3;

    private int ISO_3166_1_numeric;

    private String countryName;

    private String countryEnName;

    private String continentCode;

    private int travelWarning;

    private String warningHistory;

    public NationReturnDto(Nation nation){
        this.id = nation.getId();
        this.ISO_3166_1_alpha_2 = nation.getISO_3166_1_alpha_2();
        this.ISO_3166_1_alpha_3 = nation.getISO_3166_1_alpha_3();
        this.ISO_3166_1_numeric = nation.getISO_3166_1_numeric();
        this.countryName = nation.getCountryName();
        this.countryEnName = nation.getCountryEnName();
        this.continentCode = nation.getContinentCode();
        this.travelWarning = nation.getTravelWarning();
        this.warningHistory = nation.getWarningHistory();
    }
}
