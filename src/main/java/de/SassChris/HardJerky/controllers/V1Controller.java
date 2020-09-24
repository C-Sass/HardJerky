package de.SassChris.HardJerky.controllers;

import de.SassChris.HardJerky.entities.Verarbeitung_1;
import de.SassChris.HardJerky.services.EinkaufService;
import de.SassChris.HardJerky.services.V1Service;
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
public class V1Controller {

    private final V1Service v1Service;
    private final EinkaufService einkaufService;

    @RequestMapping("/V1")
    public String v1(Model model){
        List<Verarbeitung_1> verarbeitung1List = v1Service.listAll();
        model.addAttribute("v1List", verarbeitung1List);
        return "Vertriebskette/V1/V1";
    }

    @RequestMapping("/V1/Neu")
    public String v1Neu(Model model){
        Verarbeitung_1 v1 = new Verarbeitung_1();
        model.addAttribute("v1", v1);
        return "Vertriebskette/V1/V1_Neu";
    }

    @RequestMapping(value = "/V1/Save", method = RequestMethod.POST)
    public String saveV1(@ModelAttribute("v1") Verarbeitung_1 v1) {
        if (v1.getCharge() == null) {
            v1.setCharge(einkaufService.currentCharge());
        }
        v1Service.save(v1);
        return "redirect:/V1";
    }

    @RequestMapping("/V1/Edit{id}")
    public ModelAndView edit(@PathVariable(name = "id") long id){
        ModelAndView modelAndView = new ModelAndView("Vertriebskette/V1/V1_Edit");
        Optional<Verarbeitung_1> v1 = v1Service.getById(id);
        modelAndView.addObject("v1", v1);
        return modelAndView;
    }

    @RequestMapping("/V1/Delete{id}")
    public String delete(@PathVariable(name = "id") long id) {
        v1Service.deleteById(id);
        return "redirect:/V1";
    }
}
