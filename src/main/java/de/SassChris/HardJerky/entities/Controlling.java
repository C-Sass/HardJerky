package de.SassChris.HardJerky.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Author: Chris Saß Created on: 17.09.2020
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Controlling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long charge;

    //Einkauf & V1
    private double gekauft;
    private double verschnitt;
    private double verschnittRate;

    //V2
    private double bereit;
    private String marinade;
    private double trocken;
    private double trockenRate;
    private int packs;

    //Kalkulation
    private double kosten;
    private int ladungen;
    private double mengeLadung;

    //Controlling
    private double einnahmen;
    private double marge;
    private double margeRate;
}