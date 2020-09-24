package de.SassChris.HardJerky.marinades;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author: Chris Saß, Created on: 24.09.2020
 */

@Component
public class StrongAsian {

    public static Map<String, Integer> rezept = Stream.of(new Object[][]{
            {"Fleisch", 1500},
            {"Sojasauce",},
            {"Worcestersauce",},
            {"Salz",},
            {"Pfeffer",},
            {"Rauchsalz",},
            {"Knoblauchgranulat",},
            {"Zwiebelgranulat",},
            {"Tabasco",},
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
}
