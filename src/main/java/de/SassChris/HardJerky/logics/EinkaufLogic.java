package de.SassChris.HardJerky.logics;

import de.SassChris.HardJerky.entities.Einkauf;
import de.SassChris.HardJerky.services.EinkaufService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Chris Saß, Created on: 23.09.2020
 */

@Service
public class EinkaufLogic {

    private final EinkaufService einkaufService;

    public EinkaufLogic(EinkaufService einkaufService) {
        this.einkaufService = einkaufService;
        checkCharges();
    }

    private void checkCharges() {
        List<Einkauf> einkaufList = einkaufService.listAll();
        for (Einkauf einkauf : einkaufList) {
            if (einkauf.getCharge() != einkauf.getId()) {
                einkauf.setCharge(einkauf.getId());
                einkaufService.save(einkauf);
            }
        }
    }
}
