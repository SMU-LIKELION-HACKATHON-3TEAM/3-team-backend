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
    @Column(name = "Quote_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Quote_id;

    @Column(name = "price")
    private int price;

    @OneToOne
    @JoinColumn (name = "ISO_3166_2")
    private AdministrativeDivision administrativeDivision;


    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


}
