package com.grishare.dto;

import com.grishare.domain.Nation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class NationRequestDto {
    private String ISO_3166_1_alpha_2;

    private String ISO_3166_1_alpha_3;

    private int ISO_3166_1_numeric;

    private String countryName;

    private String countryEnName;

    private String continentCode;

    private int travelWarning;

    private String warningHistory;

    public Nation toEntity(){
        Nation nation = new Nation(
                this.ISO_3166_1_alpha_2,
                this.ISO_3166_1_alpha_3,
                this.ISO_3166_1_numeric,
                this.countryName,
                this.countryEnName,
                this.continentCode,
                this.travelWarning,
                this.warningHistory
        );
        return nation;
    }
}
