package de.SassChris.HardJerky.controllers;

import de.SassChris.HardJerky.entities.Verarbeitung_2;
import de.SassChris.HardJerky.logics.ControllingLogic;
import de.SassChris.HardJerky.logics.LagerLogic;
import de.SassChris.HardJerky.logics.MarinadeLogic;
import de.SassChris.HardJerky.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    private final LagerService lagerService;
    private final EinkaufService einkaufService;
    private final ControllingService controllingService;
    private final V1Service v1Service;
    private final KalkulationService kalkulationService;
    private final MarinadeService marinadeService;

    @RequestMapping("/V2")
    public String v2(Model model) {
        List<Verarbeitung_2> verarbeitung2List = v2Service.listAll();
        model.addAttribute("v2List", verarbeitung2List);
        return "Vertriebskette/V2/V2";
    }

    @RequestMapping("/V2/Neu")
    public String v2Neu(Model model) {
        Verarbeitung_2 v2 = new Verarbeitung_2();
        model.addAttribute("v2", v2);
        model.addAttribute("marinadeListe", MarinadeLogic.marinadeListe());
        return "Vertriebskette/V2/V2_Neu";
    }

    @RequestMapping(value = "/V2/Save", method = RequestMethod.POST)
    public String saveV2(@ModelAttribute("v2") Verarbeitung_2 v2,
                         @RequestParam(value = "fertig", required = false) String fertig) {
        if (v2.getCharge() == null) {
            v2.setCharge(einkaufService.currentCharge());
        }
        v2Service.save(v2);
        new LagerLogic(lagerService).add(v2Service.last());

        //Letzter Durchgang
        if (fertig != null) {
            new ControllingLogic(controllingService, einkaufService, v1Service, v2Service,
                    kalkulationService, marinadeService, lagerService).run(v2.getCharge());
        }

        return "redirect:/V2";
    }

    @RequestMapping("/V2/Edit{id}")
    public ModelAndView edit(@PathVariable(name = "id") long id){
        ModelAndView modelAndView = new ModelAndView("Vertriebskette/V2/V2_Edit");
        Optional<Verarbeitung_2> v2 = v2Service.getById(id);
        modelAndView.addObject("v2", v2);
        modelAndView.addObject("marinadeListe", MarinadeLogic.marinadeListe());
        return modelAndView;
    }

    @RequestMapping("/V2/Delete{id}")
    public String delete(@PathVariable(name = "id") long id) {
        v2Service.deleteById(id);
        return "redirect:/V2";
    }

}
