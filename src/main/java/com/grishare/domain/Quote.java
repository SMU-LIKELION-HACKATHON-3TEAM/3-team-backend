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

    @Column(name = "ISO_3166_2")
    private char ISO_3166_2;

    @Column(name = "item_id")
    private int item_id;


}
