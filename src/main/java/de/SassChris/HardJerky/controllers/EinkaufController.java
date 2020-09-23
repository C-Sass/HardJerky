package de.SassChris.HardJerky.controllers;

import de.SassChris.HardJerky.entities.Einkauf;
import de.SassChris.HardJerky.logics.EinkaufLogic;
import de.SassChris.HardJerky.services.EinkaufService;
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
public class EinkaufController {

    private final EinkaufService einkaufService;

    @RequestMapping("/Einkauf")
    public String einkauf(Model model){
        List<Einkauf> einkaufList = einkaufService.listAll();
        model.addAttribute("einkaufList", einkaufList);
        return "Einkauf/Einkauf";
    }

    @RequestMapping("/Einkauf/Neu")
    public String einkaufNeu(Model model){
        Einkauf einkauf = new Einkauf();
        model.addAttribute("einkauf", einkauf);
        return "Einkauf/Einkauf_Neu";
    }

    @RequestMapping(value = "/Einkauf/Save", method = RequestMethod.POST)
    public String saveEinkauf(@ModelAttribute("einkauf") Einkauf einkauf) {
        einkaufService.save(einkauf);
        new EinkaufLogic(einkaufService);
        return "redirect:/Einkauf";
    }

    @RequestMapping("/Einkauf/Edit{id}")
    public ModelAndView edit(@PathVariable(name = "id") long id){
        ModelAndView modelAndView = new ModelAndView("Einkauf/Einkauf_Edit");
        Optional<Einkauf> einkauf = einkaufService.getById(id);
        modelAndView.addObject("einkauf", einkauf);
        return modelAndView;
    }

    @RequestMapping("/Einkauf/Delete{id}")
    public String delete(@PathVariable(name = "id") long id) {
        einkaufService.deleteById(id);
        return "redirect:/Einkauf";
    }
}
