package de.SassChris.HardJerky.logics;

import de.SassChris.HardJerky.entities.Marinade;
import de.SassChris.HardJerky.marinades.StrongAsian;
import de.SassChris.HardJerky.services.MarinadeService;
import de.SassChris.HardJerky.services.ZutatenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MarinadeLogic {

    private final ZutatenService zutatenService;
    private final MarinadeService marinadeService;

    public static String[] marinadeListe() {
        return new String[]{
                "Strong Asian"
        };
    }

    public void berechneKosten() {
        marinadeService.truncate();
        for (String marinade : marinadeListe()) {
            Marinade dbMarinade = new Marinade();
            dbMarinade.setName(marinade);
            dbMarinade.setPreisPerG(getKosten(marinade));
            marinadeService.save(dbMarinade);
        }
    }

    private double getKosten(String marinade) {
        double cost = 0.0;

        switch (marinade) {
            case "Strong Asian":
                cost = calcKosten(modifyRezept(StrongAsian.rezept));
                break;
            default:
                System.out.println("Kein Rezept mit Name " + marinade + " gefunden.");
        }
        return cost;
    }

    private double calcKosten(Map<String, Double> zutatenListe) {
        double sumKosten = 0;

        for (Map.Entry<String, Double> eintrag : zutatenListe.entrySet()) {
            double kostenEinheit = zutatenService.find(eintrag.getKey()).getPreisNormiert();
            sumKosten += (eintrag.getValue() * kostenEinheit);
        }

        return sumKosten;
    }

    private Map<String, Double> modifyRezept(Map<String, Integer> rezept) {
        Map<String, Double> map = new HashMap<>();
        double fleisch = rezept.get("Fleisch");

        for (Map.Entry<String, Integer> entry : rezept.entrySet()) {
            if (entry.getKey().equals("Fleisch")) {
                continue;
            }
            map.put(entry.getKey(), (entry.getValue() / fleisch));
        }

        return map;
    }

}
