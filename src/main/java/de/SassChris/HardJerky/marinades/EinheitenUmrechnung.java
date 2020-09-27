package de.SassChris.HardJerky.marinades;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author: Chris Saß, Created on: 25.09.2020
 */


public class EinheitenUmrechnung {

    public static final Map<String, Integer> measureToUnit = Stream.of(new Object[][]{
            {"Salz", 8}, //1 TL
            {"Pfeffer, schwarz", 7}, //1 TL
            {"Rauchsalz", 3}, //1 Prise
            {"Knoblauchgranulat", 3}, //1 TL
            {"Zwiebelgranulat", 3}, //1 TL
            {"Tabasco", 1}, //1 Spritzer
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

}
