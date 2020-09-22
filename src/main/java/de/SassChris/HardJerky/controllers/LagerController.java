package de.SassChris.HardJerky.controllers;

import de.SassChris.HardJerky.entities.Lager;
import de.SassChris.HardJerky.services.LagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Author: Chris Saß, Created on: 19.09.2020
 */

@Controller
@RequiredArgsConstructor
public class LagerController {

    private final LagerService lagerService;

    @RequestMapping("/Lager")
    public String lager(Model model){
        List<Lager> lagerList = lagerService.listAll();
        model.addAttribute("lagerList", lagerList);
        return "/Lager";
    }
}
