package de.SassChris.HardJerky.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Author: Chris Saß, Created on: 24.09.2020
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Zutaten {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String zutat;
    private int menge;
    private double preis;
    private double preisNormiert;
}
