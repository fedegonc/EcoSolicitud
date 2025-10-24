package com.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RutasPageController {

    @GetMapping("/rutas/mapa")
    public String mapaRutas() {
        return "rutas";
    }
}
