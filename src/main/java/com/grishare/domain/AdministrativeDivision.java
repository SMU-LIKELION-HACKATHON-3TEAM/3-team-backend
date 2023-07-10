package com.grishare.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
@Table(name = "AdministrativeDivision")
public class AdministrativeDivision {

    @Id
    @Column(name = "iso_3166_2")
    private char id;

    @ManyToOne
    @JoinColumn(name = "nation_pk")
    private Nation nation;

    @Column(name = "adName")
    private char adName;
}
