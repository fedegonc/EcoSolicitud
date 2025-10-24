package com.tcc.service;

import com.tcc.application.RutaService;
import com.tcc.model.Solicitud;
import com.tcc.repository.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MetricsService {

    private final SolicitudRepository solicitudRepository;
    private final RutaService rutaService;

    public MetricsService(SolicitudRepository solicitudRepository, RutaService rutaService) {
        this.solicitudRepository = solicitudRepository;
        this.rutaService = rutaService;
    }

    public static record MetricOption(String key, String label) {}

    public List<MetricOption> availableMetrics() {
        return List.of(
                new MetricOption("total_solicitudes", "Total de solicitudes"),
                new MetricOption("completadas", "Solicitudes completadas"),
                new MetricOption("porcentaje_completadas", "% completadas"),
                new MetricOption("pendientes", "Solicitudes pendientes"),
                new MetricOption("barrios_con_solicitudes", "Barrios con solicitudes"),
                new MetricOption("distancia_total_estimada", "Distancia total estimada"),
                new MetricOption("tiempo_estimado_total", "Tiempo estimado total (min)"),
                new MetricOption("promedio_por_barrio", "Promedio solicitudes por barrio"),
                new MetricOption("max_por_barrio", "Máximo por barrio"),
                new MetricOption("sin_barrio", "Solicitudes sin barrio")
        );
    }

    public Map<String, String> computeMetrics(Set<String> selectedKeys) {
        List<Solicitud> all = solicitudRepository.findAll();
        Map<String, Long> porBarrio = rutaService.obtenerEstadisticasPorBarrio(all);

        long total = all.size();
        long completadas = all.stream().filter(Solicitud::isCompletada).count();
        long pendientes = total - completadas;
        long barrios = porBarrio.size();
        long sinBarrio = all.stream().filter(s -> s.getBarrio() == null).count();

        // Distancia y tiempo estimados en una ruta simple (orden por latitud)
        List<Solicitud> optimizada = rutaService.optimizarRutas(all);
        double distancia = rutaService.calcularDistanciaTotal(optimizada);
        int tiempo = (int) (distancia * 2) + (optimizada.size() * 5);

        double promedioPorBarrio = barrios > 0 ? (double) total / (double) barrios : 0.0;
        long maxPorBarrio = porBarrio.values().stream().max(Long::compareTo).orElse(0L);

        Map<String, String> result = new LinkedHashMap<>();
        for (String key : selectedKeys) {
            switch (key) {
                case "total_solicitudes" -> result.put("Total de solicitudes", String.valueOf(total));
                case "completadas" -> result.put("Solicitudes completadas", String.valueOf(completadas));
                case "porcentaje_completadas" -> {
                    double pct = total > 0 ? (completadas * 100.0 / total) : 0.0;
                    result.put("% completadas", String.format(Locale.US, "%.1f%%", pct));
                }
                case "pendientes" -> result.put("Solicitudes pendientes", String.valueOf(pendientes));
                case "barrios_con_solicitudes" -> result.put("Barrios con solicitudes", String.valueOf(barrios));
                case "distancia_total_estimada" -> result.put("Distancia total estimada", String.format(Locale.US, "%.2f", distancia));
                case "tiempo_estimado_total" -> result.put("Tiempo estimado total (min)", String.valueOf(tiempo));
                case "promedio_por_barrio" -> result.put("Promedio solicitudes por barrio", String.format(Locale.US, "%.2f", promedioPorBarrio));
                case "max_por_barrio" -> result.put("Máximo por barrio", String.valueOf(maxPorBarrio));
                case "sin_barrio" -> result.put("Solicitudes sin barrio", String.valueOf(sinBarrio));
                default -> { /* ignore */ }
            }
        }
        return result;
    }
}
