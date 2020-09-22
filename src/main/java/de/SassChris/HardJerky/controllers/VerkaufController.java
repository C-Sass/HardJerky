package de.SassChris.HardJerky.controllers;

import de.SassChris.HardJerky.entities.Verkauf;
import de.SassChris.HardJerky.services.VerkaufService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

/**
 * Author: Chris Saß, Created on: 19.09.2020
 */

@Controller
@RequiredArgsConstructor
public class VerkaufController {

    private final VerkaufService verkaufService;

    @RequestMapping("/Verkauf")
    public String verkauf(Model model){
        List<Verkauf> verkaufList = verkaufService.listAll();
        model.addAttribute("verkaufList", verkaufList);
        return "Verkauf/Verkauf";
    }

    @RequestMapping("/Verkauf/Neu")
    public String verkaufNeu(Model model){
        Verkauf verkauf = new Verkauf();
        model.addAttribute("verkauf", verkauf);
        return "Verkauf/Verkauf_Neu";
    }

    @RequestMapping(value = "/Verkauf/Save", method = RequestMethod.POST)
    public String saveVerkauf(@ModelAttribute("verkauf") Verkauf verkauf) {
        verkaufService.save(verkauf);
        return "redirect:/Verkauf";
    }

    @RequestMapping("/Verkauf/Edit{id}")
    public ModelAndView edit(@PathVariable(name = "id") long id){
        ModelAndView modelAndView = new ModelAndView("Verkauf/Verkauf_Edit");
        Optional<Verkauf> verkauf = verkaufService.getById(id);
        modelAndView.addObject("verkauf", verkauf);
        return modelAndView;
    }

    @RequestMapping("/Verkauf/Delete{id}")
    public String delete(@PathVariable(name = "id") long id) {
        verkaufService.deleteById(id);
        return "redirect:/Verkauf";
    }

}
