package de.SassChris.HardJerky.logics;

import de.SassChris.HardJerky.entities.Kalkulation;
import de.SassChris.HardJerky.services.KalkulationService;
import de.SassChris.HardJerky.services.MarinadeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Author: Chris Saß, Created on: 25.09.2020
 */

@Service
@RequiredArgsConstructor
public class KalkulationLogic {

    //Kosten Gesamt
    public static final double MENGE_PRO_LADUNG = 1.1;
    public static final double FLEISCH_PREIS_KG = 14.90;
    public static final double STROM_PREIS_KWH = 0.2826;
    public static final int DRY_TIME = 14;
    public static final double VERSCHNITT_RATE = 0.045;
    public static final double DRYER_KWH = 0.35;
    public static final double DRYER_PREIS_STUNDE = STROM_PREIS_KWH * DRYER_KWH;
    public static final double DRYER_KOSTEN = DRY_TIME * DRYER_PREIS_STUNDE;
    public static final double LOHN = 15 * 1.5;
    public static final double FAHRT_KM = 18.8;
    public static final double FAHRTKOSTEN = 0.3 * FAHRT_KM;
    //Kosten Pro Packung
    public static final double DRY_RATE = 0.39;
    public static final double BEUTEL = 0.17;
    public static final double GEWINN_AUFSCHLAG = 0.2;
    public static final int GRAMM_BEUTEL = 50;
    private final MarinadeService marinadeService;
    private final KalkulationService kalkulationService;
    //Preis, Menge
    private final SortedMap<Double, Double> sortedMap = new TreeMap<>();
    private double trocken;

    public void kalkuliere(String marinade) {
        kalkulationService.truncate();
        SortedMap<Double, Double> map = getKalkulationMap(marinade);
        for (Map.Entry<Double, Double> entry : map.entrySet()) {
            Kalkulation kalkulation = new Kalkulation();
            kalkulation.setPreis(Precision.round(entry.getKey(), 2));
            kalkulation.setMenge(entry.getValue());
            kalkulationService.save(kalkulation);
        }
    }

    private SortedMap<Double, Double> getKalkulationMap(String marinade) {
        fillMap(marinade);
        return sortedMap;
    }

    private void fillMap(String marinade) {
        for (double i = 1.0; i <= 10; i += 0.5) {
            double kostenGesamt = getKostenGesamt(i, marinade);
            int packs = (int) Math.floor((trocken * 1000) / GRAMM_BEUTEL);
            double kostenPackung = getKostenPackung(kostenGesamt, packs);

            sortedMap.put(kostenPackung, i);
        }
    }

    private double getKostenGesamt(double menge, String marinade) {
        double kosten = FAHRTKOSTEN;
        kosten += (menge * FLEISCH_PREIS_KG);

        double verschnitt = VERSCHNITT_RATE * menge;
        double mengeReady = menge - verschnitt;
        kosten += (mengeReady * 1000) * getNormKostenMarinade(marinade);
        trocken = mengeReady * DRY_RATE;

        int ladungen = (int) Math.ceil(mengeReady / MENGE_PRO_LADUNG);
        kosten += kostenLadung(ladungen);

        return kosten;
    }

    private double getKostenPackung(double kostenGesamt, int packs) {
        double kosten = 0.0;
        double anteil = kostenGesamt / packs;
        kosten += anteil;
        kosten += BEUTEL;
        kosten *= (1 + GEWINN_AUFSCHLAG);

        return kosten;
    }

    private double getNormKostenMarinade(String marinade) {
        return marinadeService.find(marinade).getPreisPerG();
    }

    public double kostenLadung(int ladungen) {
        double kosten = 0.0;
        kosten += ladungen * DRYER_KOSTEN;
        kosten += ladungen * LOHN;
        return kosten;
    }

    public double kostenMarinade(double menge, String marinade) {
        return (menge * 1000) * getNormKostenMarinade(marinade);
    }
}