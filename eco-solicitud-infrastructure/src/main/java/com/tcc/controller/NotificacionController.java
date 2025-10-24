package com.tcc.controller;

import com.tcc.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notificaciones")
public class NotificacionController {
    
    @Autowired
    private NotificacionService notificacionService;
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("notificaciones", notificacionService.obtenerTodas());
        model.addAttribute("noLeidas", notificacionService.contarNoLeidas());
        return "notificaciones";
    }
    
    @PostMapping("/{id}/marcar-leida")
    public String marcarLeida(@PathVariable Long id) {
        notificacionService.marcarComoLeida(id);
        return "redirect:/notificaciones";
    }
}
