package de.SassChris.HardJerky.logics;

import de.SassChris.HardJerky.marinades.StrongAsian;
import de.SassChris.HardJerky.services.ZutatenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MarinadeLogic {

    private final ZutatenService zutatenService;

    public static String[] marinadeListe() {
        return new String[]{
                "Strong Asian"
        };
    }

    public double getKosten(String marinade, Double menge) {
        double cost = 0.0;

        switch (marinade) {
            case "Strong Asian":
                calcKosten(modifyRezept(StrongAsian.rezept, menge));
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

    private Map<String, Double> modifyRezept(Map<String, Integer> rezept, Double menge) {
        Map<String, Double> map = new HashMap<>();
        double fleisch = rezept.get("Fleisch");

        for (Map.Entry<String, Integer> entry : rezept.entrySet()) {
            double norm = entry.getValue() / fleisch;
            map.put(entry.getKey(), norm * menge);
        }

        return map;
    }

}
