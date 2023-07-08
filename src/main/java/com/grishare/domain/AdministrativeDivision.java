package com.grishare.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Getter
@Table(name = "AdministrativeDivision")
public class AdministrativeDivision {

    @Id
    private char iso_3166_2;

    @Column(name = "nation_pk")
    private int nation_pk;

    @Column(name = "adName")
    private char adName;
}
