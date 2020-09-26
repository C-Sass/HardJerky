package de.SassChris.HardJerky.controllers;

import de.SassChris.HardJerky.entities.Kalkulation;
import de.SassChris.HardJerky.logics.KalkulationLogic;
import de.SassChris.HardJerky.logics.MarinadeLogic;
import de.SassChris.HardJerky.services.KalkulationService;
import de.SassChris.HardJerky.services.MarinadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Author: Chris Saß, Created on: 25.09.2020
 */

@Controller
@RequiredArgsConstructor
public class KalkulationController {

    private final MarinadeService marinadeService;
    private final KalkulationService kalkulationService;

    @RequestMapping("/Kalkulation")
    public String kalkulation(Model model) {
        List<Kalkulation> list = kalkulationService.kalkulationList();
        model.addAttribute("calcList", list);
        model.addAttribute("marinadeListe", MarinadeLogic.marinadeListe());
        return "Kontrolle/Kalkulation/Kalkulation";
    }

    @RequestMapping("/Kalkulation/Neu")
    public String neu(Model model) {
        Kalkulation kalkulation = new Kalkulation();
        model.addAttribute("calc", kalkulation);
        model.addAttribute("marinadeListe", MarinadeLogic.marinadeListe());
        return "Kontrolle/Kalkulation/Kalkulation_Neu";
    }

    @RequestMapping(value = "Kalkulation/Save", method = RequestMethod.POST)
    public String neueKalkulation(@ModelAttribute("calc") Kalkulation kalkulation) {
        String marinade = kalkulation.getMarinade();
        if (marinade != null && ! marinade.equals("")) {
            new KalkulationLogic(marinadeService, kalkulationService).kalkuliere(marinade);
        } else {
            System.out.println("Marinade: " + marinade + " ist nicht gültig.");
        }
        return "redirect:/Kalkulation";
    }
}
