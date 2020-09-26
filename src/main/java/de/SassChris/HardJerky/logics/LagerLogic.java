package de.SassChris.HardJerky.logics;

import de.SassChris.HardJerky.entities.Lager;
import de.SassChris.HardJerky.entities.Verarbeitung_2;
import de.SassChris.HardJerky.entities.Verkauf;
import de.SassChris.HardJerky.services.LagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Author: Chris Saß, Created on: 23.09.2020
 */

@Service
@RequiredArgsConstructor
public class LagerLogic {

    private final LagerService lagerService;

    public void remove(Verkauf verkauf) {
        String marinade = verkauf.getMarinade();
        long id = lagerService.getByMarinade(marinade).getId();
        double preis = lagerService.getByMarinade(marinade).getPreis();
        int packs = lagerService.getByMarinade(marinade).getPacks() - verkauf.getPacks();

        Lager lager = new Lager(id, marinade, packs, preis);
        lagerService.save(lager);
    }

    public void add(Verarbeitung_2 v2) {
        long id;
        String marinade = v2.getMarinade();
        int packs;
        double preis = 0.0; //in Controlling geändert

        if (lagerService.last() == null) {
            id = 0;
            packs = v2.getPacks();
        } else if (lagerService.getByMarinade(marinade) != null) {
            id = lagerService.getByMarinade(marinade).getId();
            packs = lagerService.getByMarinade(marinade).getPacks() + v2.getPacks();
        } else {
            id = lagerService.last().getId() + 1;
            packs = v2.getPacks();
        }

        Lager lager = new Lager(id, marinade, packs, preis);
        lagerService.save(lager);
    }
}
