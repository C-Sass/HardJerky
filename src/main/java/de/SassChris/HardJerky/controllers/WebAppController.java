package de.SassChris.HardJerky.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author: Chris Saß, Created on: 18.09.2020
 */

@Controller
public class WebAppController {

    @RequestMapping({"/", "/Dashboard"})
    public String dashboard() {
        return "Dashboard";
    }
}
