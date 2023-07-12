package com.grishare.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@Table(name = "Classification")
@AllArgsConstructor
@NoArgsConstructor
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int classification_id;

    @Column(name = "classificationName")
    private char classificationName;
}
