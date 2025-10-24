package com.tcc.controller;

import com.tcc.dto.OrganizacionForm;
import com.tcc.model.Organizacion;
import com.tcc.repository.OrganizacionRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/organizaciones")
public class OrganizacionController {

    private final OrganizacionRepository repo;

    public OrganizacionController(OrganizacionRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/crear")
    public String mostrarFormulario(Model model) {
        model.addAttribute("organizacion", new OrganizacionForm());
        return "organizacion-form";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute("organizacion") @Valid OrganizacionForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "organizacion-form";
        }
        Organizacion org = new Organizacion(form.getNombre(), form.getDireccion(), form.getTelefono(), form.getLat(), form.getLng());
        repo.save(org);
        return "redirect:/organizaciones";
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("organizaciones", repo.findAll());
        return "organizaciones-lista";
    }
}
