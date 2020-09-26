package de.SassChris.HardJerky.controllers;

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

    @RequestMapping({"/", "/Dashboard"})
    public String dashboard(Model model) {
        int monat = LocalDate.now().getMonthValue();
        model.addAttribute("monat", monat);
        return "Dashboard";
    }
}
