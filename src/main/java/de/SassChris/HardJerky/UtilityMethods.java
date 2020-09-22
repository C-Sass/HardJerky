package de.SassChris.HardJerky;

import de.SassChris.HardJerky.services.EinkaufService;

/**
 * Author: Chris Saß, Created on: 21.09.2020
 */

public class UtilityMethods {

    public static long currentCharge() {
        EinkaufService einkauf_service = new EinkaufService();
        return einkauf_service.last().getCharge();
    }

}
