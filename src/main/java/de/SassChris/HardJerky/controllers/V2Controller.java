package de.SassChris.HardJerky.controllers;

import de.SassChris.HardJerky.UtilityMethods;
import de.SassChris.HardJerky.entities.Verarbeitung_2;
import de.SassChris.HardJerky.services.V2Service;
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
public class V2Controller {

    private final V2Service v2Service;

    @RequestMapping("/V2")
    public String v2(Model model){
        List<Verarbeitung_2> verarbeitung2List = v2Service.listAll();
        model.addAttribute("v2List", verarbeitung2List);
        return "V2/V2";
    }

    @RequestMapping("/V2/Neu")
    public String v2Neu(Model model){
        Verarbeitung_2 v2 = new Verarbeitung_2();
        v2.setCharge(UtilityMethods.currentCharge());
        model.addAttribute("v2", v2);
        return "V2/V2_Neu";
    }

    @RequestMapping(value = "/V2/Save", method = RequestMethod.POST)
    public String saveV2(@ModelAttribute("v2") Verarbeitung_2 v2) {
        v2Service.save(v2);
        return "redirect:/V2";
    }

    @RequestMapping("/V2/Edit{id}")
    public ModelAndView edit(@PathVariable(name = "id") long id){
        ModelAndView modelAndView = new ModelAndView("V2/V2_Edit");
        Optional<Verarbeitung_2> v2 = v2Service.getById(id);
        modelAndView.addObject("v2", v2);
        return modelAndView;
    }

    @RequestMapping("/V2/Delete{id}")
    public String delete(@PathVariable(name = "id") long id) {
        v2Service.deleteById(id);
        return "redirect:/V2";
    }

}
