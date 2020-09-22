package de.SassChris.HardJerky.controllers;

import de.SassChris.HardJerky.services.EinkaufService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

/**
 * Author: Chris Saß, Created on: 21.09.2020
 */

@WebMvcTest
class EinkaufControllerTest {

    @BeforeEach
    void initialize(){
        EinkaufService einkaufService = new EinkaufService();
    }

    @Test
    void einkauf() {
        //nix zu testen
    }

    @Test
    void einkaufNeu() {
    }

    @Test
    void saveEinkauf() {
    }

    @Test
    void edit() {
    }

    @Test
    void delete() {
    }
}