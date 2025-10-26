package com.tcc.controller;

import com.tcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/registro")
    public String registro(
            @RequestParam String nombre,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String telefono,
            RedirectAttributes redirectAttributes) {
        try {
            usuarioService.registrar(nombre, email, password, telefono);
            redirectAttributes.addFlashAttribute("mensaje", "Registro exitoso. Por favor, inicia sesión.");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }
}
