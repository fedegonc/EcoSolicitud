package com.tcc.controller;

import com.tcc.dto.RutaBarrioDTO;
import com.tcc.dto.SolicitudDTO;
import com.tcc.model.Solicitud;
import com.tcc.repository.SolicitudRepository;
import com.tcc.application.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rutas")
@CrossOrigin(origins = "*")
public class RutaController {

    @Autowired
    private RutaService rutaService;

    @Autowired
    private SolicitudRepository solicitudRepo;

    @GetMapping("/solicitudes")
    public List<SolicitudDTO> obtenerSolicitudes() {
        return solicitudRepo.findAll().stream()
                .map(this::convertirASolicitudDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/optimizadas")
    public List<RutaBarrioDTO> obtenerRutasOptimizadas(
            @RequestParam(defaultValue = "0.0") double latInicio,
            @RequestParam(defaultValue = "0.0") double lngInicio) {
        List<Solicitud> solicitudes = solicitudRepo.findAll();
        List<Solicitud> rutasOptimizadas = rutaService.optimizarRutasPorBarrio(
                solicitudes, latInicio, lngInicio);
        return agruparPorBarrioDTO(rutasOptimizadas);
    }

    @GetMapping("/estadisticas")
    public Map<String, Long> obtenerEstadisticas() {
        List<Solicitud> solicitudes = solicitudRepo.findAll();
        return rutaService.obtenerEstadisticasPorBarrio(solicitudes);
    }

    @GetMapping("/metricas")
    public Map<String, Object> obtenerMetricas(
            @RequestParam(defaultValue = "0.0") double latInicio,
            @RequestParam(defaultValue = "0.0") double lngInicio) {
        List<Solicitud> solicitudes = solicitudRepo.findAll();
        List<Solicitud> rutasOptimizadas = rutaService.optimizarRutasPorBarrio(
                solicitudes, latInicio, lngInicio);
        double distanciaTotal = rutaService.calcularDistanciaTotal(rutasOptimizadas);
        int tiempoEstimado = (int) (distanciaTotal * 2) + (rutasOptimizadas.size() * 5);
        return Map.of(
                "distanciaTotal", distanciaTotal,
                "tiempoEstimadoMinutos", tiempoEstimado,
                "totalSolicitudes", rutasOptimizadas.size(),
                "totalBarrios", rutaService.obtenerEstadisticasPorBarrio(solicitudes).size()
        );
    }

    private SolicitudDTO convertirASolicitudDTO(Solicitud s) {
        return new SolicitudDTO(
                s.getId(),
                s.getDescripcion(),
                s.getLat(),
                s.getLng(),
                s.getUsuario() != null ? s.getUsuario().getNombre() : null,
                s.getBarrio() != null ? s.getBarrio().getNombre() : "Sin Barrio",
                s.isCompletada()
        );
    }

    private List<RutaBarrioDTO> agruparPorBarrioDTO(List<Solicitud> solicitudes) {
        List<RutaBarrioDTO> resultado = new ArrayList<>();
        String barrioActual = null;
        List<SolicitudDTO> solicitudesActuales = new ArrayList<>();
        for (Solicitud s : solicitudes) {
            String nombreBarrio = s.getBarrio() != null ? s.getBarrio().getNombre() : "Sin Barrio";
            if (!nombreBarrio.equals(barrioActual)) {
                if (barrioActual != null) {
                    resultado.add(new RutaBarrioDTO(barrioActual, new ArrayList<>(solicitudesActuales)));
                    solicitudesActuales.clear();
                }
                barrioActual = nombreBarrio;
            }
            solicitudesActuales.add(convertirASolicitudDTO(s));
        }
        if (barrioActual != null) {
            resultado.add(new RutaBarrioDTO(barrioActual, solicitudesActuales));
        }
        return resultado;
    }
}
