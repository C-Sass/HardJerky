package de.SassChris.HardJerky.logics;

import de.SassChris.HardJerky.services.EinkaufService;
import lombok.RequiredArgsConstructor;

/**
 * Author: Chris Saß, Created on: 21.09.2020
 */

@RequiredArgsConstructor
public class UtilityMethods {

    private final EinkaufService einkaufService;

    public static String[] marinadeListe() {
        return new String[]{
                "Strong Asian"
        };
    }

    public long currentCharge() {
        return einkaufService.last().getCharge();
    }

}
