package com.grishare.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "Item")
@Entity @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int item_id;

    @ManyToOne
    @JoinColumn(name = "classification_id")
    private Classification classification;

    @Column(name = "ItemName")
    private char itemName;
}
