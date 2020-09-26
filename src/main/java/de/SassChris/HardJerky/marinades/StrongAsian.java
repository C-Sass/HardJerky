package de.SassChris.HardJerky.marinades;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author: Chris Saß, Created on: 24.09.2020
 */

public class StrongAsian {

    public static Map<String, Integer> rezept = Stream.of(new Object[][]{
            {"Fleisch", 1500},
            {"Sojasauce", 250},
            {"Worcestersauce", 150},
            {"Salz", EinheitenUmrechnung.measureToUnit.get("Salz")}, //1 TL
            {"Pfeffer, schwarz", EinheitenUmrechnung.measureToUnit.get("Pfeffer, schwarz")}, //1 TL
            {"Rauchsalz", EinheitenUmrechnung.measureToUnit.get("Rauchsalz")}, //1 Prise
            {"Knoblauchgranulat", EinheitenUmrechnung.measureToUnit.get("Knoblauchgranulat")}, //1 TL
            {"Zwiebelgranulat", EinheitenUmrechnung.measureToUnit.get("Zwiebelgranulat")}, //1 TL
            {"Tabasco", 6 * EinheitenUmrechnung.measureToUnit.get("Tabasco")}, //6 Spritzer
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
}
