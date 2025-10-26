package com.tcc.controller;

import com.tcc.dto.SolicitudForm;
import com.tcc.model.Solicitud;
import com.tcc.model.Organizacion;
import com.tcc.repository.SolicitudRepository;
import com.tcc.repository.OrganizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tcc.model.EstadoSolicitud;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/solicitud")
public class SolicitudController {

    @Autowired
    private SolicitudRepository solicitudRepository;
    
    @Autowired
    private OrganizacionRepository organizacionRepository;

    @GetMapping("/crear")
    public String mostrarFormulario(Model model) {
        model.addAttribute("solicitud", new SolicitudForm());
        model.addAttribute("organizaciones", organizacionRepository.findAll());
        return "solicitud-form";
    }

    @PostMapping("/crear")
    public String crearSolicitud(@ModelAttribute("solicitud") @Valid SolicitudForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("organizaciones", organizacionRepository.findAll());
            return "solicitud-form";
        }

        Organizacion org = organizacionRepository.findById(form.getOrganizacionId()).orElse(null);
        Solicitud solicitud = new Solicitud(form.getDescripcion(), form.getLat(), form.getLng(), org != null ? org.getNombre() : "Sin organización");
        solicitud.setOrganizacionId(form.getOrganizacionId());
        solicitudRepository.save(solicitud);
        return "redirect:/solicitud/lista";
    }

    @GetMapping("/lista")
    public String listarSolicitudes(Model model, String ordenar) {
        var solicitudes = solicitudRepository.findAll();
        // Calcular distancia para cada solicitud
        solicitudes.forEach(s -> {
            if (s.getOrganizacionId() != null) {
                Organizacion org = organizacionRepository.findById(s.getOrganizacionId()).orElse(null);
                if (org != null && org.getLat() != null && org.getLng() != null) {
                    double dist = haversine(s.getLat(), s.getLng(), org.getLat(), org.getLng());
                    s.setDistancia(Double.valueOf(dist));
                }
            }
        });
        
        // Ordenar según parámetro
        if ("distancia".equals(ordenar)) {
            solicitudes.sort((a, b) -> {
                Double distA = a.getDistancia() != null ? a.getDistancia() : Double.MAX_VALUE;
                Double distB = b.getDistancia() != null ? b.getDistancia() : Double.MAX_VALUE;
                return distA.compareTo(distB);
            });
        } else if ("llegada".equals(ordenar)) {
            solicitudes.sort((a, b) -> b.getId().compareTo(a.getId())); // Más recientes primero
        }
        
        model.addAttribute("solicitudes", solicitudes);
        model.addAttribute("ordenarActual", ordenar != null ? ordenar : "llegada");
        return "solicitud-lista";
    }
    
    private double haversine(double lat1, double lng1, double lat2, double lng2) {
        final int R = 6371; // km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    @GetMapping("/{id}/ruta")
    public String verRuta(@PathVariable Long id, Model model) {
        Solicitud solicitud = solicitudRepository.findById(id).orElse(null);
        if (solicitud == null) {
            return "redirect:/solicitud/lista";
        }
        
        // Buscar la organización por nombre (entidadRecolectora)
        // Para simplificar, asumimos que entidadRecolectora contiene el nombre de la org
        model.addAttribute("solicitud", solicitud);
        model.addAttribute("organizaciones", organizacionRepository.findAll());
        return "solicitud-ruta";
    }

    @PostMapping("/{id}/cambiar-estado")
    public String cambiarEstado(@PathVariable Long id, @RequestParam String nuevoEstado) {
        Solicitud solicitud = solicitudRepository.findById(id).orElse(null);
        if (solicitud != null) {
            try {
                EstadoSolicitud estado = EstadoSolicitud.valueOf(nuevoEstado);
                solicitud.setEstado(estado);
                solicitudRepository.save(solicitud);
            } catch (IllegalArgumentException e) {
                // Estado inválido
            }
        }
        return "redirect:/solicitud/lista";
    }
}
