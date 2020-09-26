package de.SassChris.HardJerky.logics;

import de.SassChris.HardJerky.entities.Controlling;
import de.SassChris.HardJerky.entities.Lager;
import de.SassChris.HardJerky.entities.Verarbeitung_1;
import de.SassChris.HardJerky.entities.Verarbeitung_2;
import de.SassChris.HardJerky.services.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Chris Saß, Created on: 26.09.2020
 */

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ControllingLogic {

    public static double vkPreis;
    private final ControllingService controllingService;
    private final EinkaufService einkaufService;
    private final V1Service v1Service;
    private final V2Service v2Service;
    private final KalkulationService kalkulationService;
    private final MarinadeService marinadeService;
    private final LagerService lagerService;

    public void run(Long charge) {
        Controlling controlling = new Controlling();
        controlling.setCharge(charge);

        addEinkauf(charge, controlling);
        addV1(charge, controlling, controlling.getGekauft());
        addV2(charge, controlling);
        addCalc(charge, controlling);
        addControlling(controlling);
        controllingService.save(controlling);

        updateLager(controlling.getMarinade());
    }

    private void addEinkauf(Long charge, Controlling controlling) {
        double menge = einkaufService.getByCharge(charge).getMenge();
        controlling.setGekauft(menge);
    }

    private void addV1(Long charge, Controlling controlling, double mengeGekauft) {
        List<Verarbeitung_1> list = v1Service.getByCharge(charge);
        double gekauft = 0.0;
        double verschnitt = 0.0;
        double bereit = 0.0;
        double verschnittRate;

        for (Verarbeitung_1 v1 : list) {
            gekauft += v1.getGekauft();
            verschnitt += v1.getVerschnitt();
            bereit = v1.getBereit();
        }

        double verschnittPlus = mengeGekauft - gekauft;
        verschnitt += verschnittPlus;

        verschnittRate = Precision.round(((verschnitt / mengeGekauft) * 100), 2);

        controlling.setVerschnitt(verschnitt);
        controlling.setBereit(bereit);
        controlling.setVerschnittRate(verschnittRate);
    }

    private void addV2(Long charge, Controlling controlling) {
        List<Verarbeitung_2> list = v2Service.getByCharge(charge);
        String marinade = list.get(0).getMarinade();

        double mengeLadung;
        double trocken = 0.0;
        int packs = 0;
        double trockenRate;

        for (Verarbeitung_2 v2 : list) {
            trocken += v2.getTrocken();
            packs += v2.getPacks();
        }

        mengeLadung = controlling.getBereit() / list.size();
        trockenRate = Precision.round((trocken / controlling.getBereit()) * 100, 2);

        controlling.setMarinade(marinade);
        controlling.setTrocken(trocken);
        controlling.setPacks(packs);
        controlling.setTrockenRate(trockenRate);
        controlling.setLadungen(list.size());
        controlling.setMengeLadung(mengeLadung);
    }

    private void addCalc(Long charge, Controlling controlling) {
        KalkulationLogic kalkulationLogic = new KalkulationLogic(marinadeService, kalkulationService);
        double kosten;
        double kostenFleisch = einkaufService.getByCharge(charge).getPreis();
        double kostenMarinade = kalkulationLogic.kostenMarinade(controlling.getBereit(), controlling.getMarinade());
        double kostenLadung = kalkulationLogic.kostenLadung(controlling.getLadungen());

        kosten = KalkulationLogic.FAHRTKOSTEN + kostenFleisch + kostenMarinade + kostenLadung;

        controlling.setKosten(kosten);
    }

    private void addControlling(Controlling controlling) {
        double einnahmen;
        double marge;
        double margeRate;
        double kosten;

        berechneVkPreis(controlling);

        kosten = controlling.getKosten() + (controlling.getPacks() * KalkulationLogic.BEUTEL);
        einnahmen = controlling.getPacks() * vkPreis;
        marge = einnahmen - kosten;
        margeRate = Precision.round((marge / einnahmen), 2);

        controlling.setEinnahmen(einnahmen);
        controlling.setMarge(marge);
        controlling.setMargeRate(margeRate);
    }

    private void updateLager(String marinade) {
        Lager lager = lagerService.getByMarinade(marinade);
        lager.setPreis(vkPreis);
        lagerService.save(lager);
    }

    private void berechneVkPreis(Controlling controlling) {
        double kosten = controlling.getKosten();
        double euro = (int) kosten;
        int cents = (int) ((kosten - (int) kosten) * 100);
        double preis;

        if (cents < 50) {
            preis = euro + 0.5;
        } else {
            preis = euro + 1;
        }
        vkPreis = preis;
    }
}