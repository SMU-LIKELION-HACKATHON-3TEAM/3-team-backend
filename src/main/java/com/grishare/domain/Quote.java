package com.grishare.domain;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "Quote")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "meal")
    private Float meal;

    @Column(name = "taxi")
    private Float taxi;

    @Column(name = "coffee")
    private Float coffee;

    @Column(name = "rice")
    private Float rice;

    @OneToOne
    @JoinColumn(name = "ad_id")
    private AdministrativeDivision administrativeDivision;

}
