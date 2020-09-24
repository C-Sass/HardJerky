package de.SassChris.HardJerky.controllers;

import de.SassChris.HardJerky.entities.Zutaten;
import de.SassChris.HardJerky.logics.ZutatenLogic;
import de.SassChris.HardJerky.services.ZutatenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * Author: Chris Saß, Created on: 24.09.2020
 */

@Controller
@RequiredArgsConstructor
public class ZutatenController {

    private final ZutatenService zutatenService;

    @RequestMapping("/Zutaten")
    public String zutatenListe(Model model) {
        model.addAttribute("zutaten", zutatenService.listAll());
        return "Marinaden/Zutaten/Zutaten";
    }

    @RequestMapping("/Zutaten/Neu")
    public String neu(Model model) {
        Zutaten zutaten = new Zutaten();
        model.addAttribute("zutat", zutaten);
        return "Marinaden/Zutaten/Zutaten_Neu";
    }

    @RequestMapping(value = "/Zutaten/Save", method = RequestMethod.POST)
    public String save(@ModelAttribute("zutat") Zutaten zutaten) {
        zutatenService.save(zutaten);
        new ZutatenLogic(zutatenService).calc();
        return "redirect:/Zutaten";
    }

    @RequestMapping("/Zutaten/Edit{id}")
    public ModelAndView edit(@PathVariable(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView("Marinaden/Zutaten/Zutaten_Edit");
        Optional<Zutaten> zutaten = zutatenService.findById(id);
        modelAndView.addObject("zutat", zutaten);
        return modelAndView;
    }

    @RequestMapping("/Zutaten/Delete{id}")
    public String delete(@PathVariable(name = "id") long id) {
        zutatenService.deleteById(id);
        return "redirect:/Zutaten";
    }
}
