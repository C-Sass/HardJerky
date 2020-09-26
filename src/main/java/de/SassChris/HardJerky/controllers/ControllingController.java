package de.SassChris.HardJerky.controllers;

import de.SassChris.HardJerky.entities.Controlling;
import de.SassChris.HardJerky.services.ControllingService;
import de.SassChris.HardJerky.services.EinkaufService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Author: Chris Saß, Created on: 26.09.2020
 */

@Controller
@RequiredArgsConstructor
public class ControllingController {

    private final EinkaufService einkaufService;
    private final ControllingService controllingService;

    @RequestMapping("/Controlling")
    public String controlling() {
        long charge = einkaufService.currentCharge();
        return "redirect:/Controlling/" + charge;
    }

    @RequestMapping("/Controlling/{charge}")
    public String controllingCharge(Model model, @PathVariable long charge) {
        Controlling controlling = controllingService.getByCharge(charge);
        if (controlling == null) {
            System.out.println("Kein Eintrag zu der Charge!");
            return "Dashboard";
        }
        model.addAttribute("charge", charge);
        model.addAttribute("controlling", controlling);
        String info =
                String.format("%-15s:%d %n %-15s:%s", "Aktuelle Charge", charge, "Marinade", controlling.getMarinade());
        model.addAttribute("info", info);
        return "Kontrolle/Controlling/Controlling";
    }

    @RequestMapping("Controlling/Change")
    public String change(Model model) {
        Controlling controlling = new Controlling();
        int lastCharge = (int) einkaufService.currentCharge();
        Long charge = null;
        model.addAttribute("charge", charge);
        model.addAttribute("lastCharge", lastCharge);
        model.addAttribute("controlling", controlling);

        return "Kontrolle/Controlling/Controlling_Change";
    }

    @RequestMapping(value = "Controlling/Save", method = RequestMethod.POST)
    public String save(@ModelAttribute("controlling") Controlling controlling) {
        long charge = controlling.getCharge();
        return "redirect:/Controlling/" + charge;
    }

}
