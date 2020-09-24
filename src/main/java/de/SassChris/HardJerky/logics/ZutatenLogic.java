package de.SassChris.HardJerky.logics;

import de.SassChris.HardJerky.entities.Zutaten;
import de.SassChris.HardJerky.services.ZutatenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Chris Saß, Created on: 24.09.2020
 */

@Service
@RequiredArgsConstructor
public class ZutatenLogic {

    private final ZutatenService zutatenService;

    public void calc() {
        List<Zutaten> zutatenList = zutatenService.listAll();
        double norm;

        for (Zutaten zutat : zutatenList) {
            norm = zutat.getPreis() / zutat.getMenge();
            zutat.setPreisNormiert(norm);
            zutatenService.save(zutat);
        }
    }
}
