package de.SassChris.HardJerky.controllers;

import de.SassChris.HardJerky.services.EinkaufService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

/**
 * Author: Chris Saß, Created on: 18.09.2020
 */

@Controller
@RequiredArgsConstructor
public class WebAppController {

    private final EinkaufService einkaufService;

    @RequestMapping({"/", "/Dashboard"})
    public String dashboard(Model model) {
        int monat = LocalDate.now().getMonthValue();
        model.addAttribute("monat", monat);
        return "Dashboard";
    }

    @RequestMapping("/Controlling")
    public String controlling(Model model) {
        int lastCharge = (int) einkaufService.last().getCharge();
        model.addAttribute("lastCharge", lastCharge);

        for (int i = 0; i <= lastCharge; i++) {
            model.addAttribute("charge" + i, einkaufService);
        }

        return "Controlling";
    }
}
