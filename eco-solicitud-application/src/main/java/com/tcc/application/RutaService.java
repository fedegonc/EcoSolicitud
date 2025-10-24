package com.tcc.application;

import com.tcc.model.Barrio;
import com.tcc.model.Ruta;
import com.tcc.model.Solicitud;

import java.util.*;
import java.util.stream.Collectors;

public class RutaService {
    public List<Solicitud> optimizarRutas(List<Solicitud> solicitudes) {
        if (solicitudes == null || solicitudes.isEmpty()) {
            return new ArrayList<>();
        }
        return solicitudes.stream()
                .sorted(Comparator.comparingDouble(Solicitud::getLat))
                .toList();
    }

    public double calcularDistancia(Solicitud s1, Solicitud s2) {
        double deltaLat = s2.getLat() - s1.getLat();
        double deltaLng = s2.getLng() - s1.getLng();
        return Math.sqrt(deltaLat * deltaLat + deltaLng * deltaLng);
        }

    public double calcularDistanciaTotal(List<Solicitud> ruta) {
        if (ruta == null || ruta.size() < 2) {
            return 0.0;
        }
        double distanciaTotal = 0.0;
        for (int i = 0; i < ruta.size() - 1; i++) {
            distanciaTotal += calcularDistancia(ruta.get(i), ruta.get(i + 1));
        }
        return distanciaTotal;
    }

    public List<Solicitud> optimizarRutasNearestNeighbor(List<Solicitud> solicitudes, Solicitud inicio) {
        if (solicitudes == null || solicitudes.isEmpty()) {
            return new ArrayList<>();
        }
        List<Solicitud> pendientes = new ArrayList<>(solicitudes);
        List<Solicitud> ruta = new ArrayList<>();
        Solicitud actual = inicio;
        ruta.add(actual);
        pendientes.remove(actual);
        while (!pendientes.isEmpty()) {
            Solicitud masCercana = encontrarMasCercana(actual, pendientes);
            ruta.add(masCercana);
            pendientes.remove(masCercana);
            actual = masCercana;
        }
        return ruta;
    }

    private Solicitud encontrarMasCercana(Solicitud origen, List<Solicitud> candidatas) {
        return candidatas.stream()
                .min(Comparator.comparingDouble(s -> calcularDistancia(origen, s)))
                .orElse(null);
    }

    public List<Solicitud> optimizarRutasPorBarrio(List<Solicitud> solicitudes, double latInicio, double lngInicio) {
        if (solicitudes == null || solicitudes.isEmpty()) {
            return new ArrayList<>();
        }
        Map<Barrio, List<Solicitud>> solicitudesPorBarrio = solicitudes.stream()
                .filter(s -> s.getBarrio() != null)
                .collect(Collectors.groupingBy(Solicitud::getBarrio));
        List<Solicitud> sinBarrio = solicitudes.stream()
                .filter(s -> s.getBarrio() == null)
                .sorted(Comparator.comparingDouble(Solicitud::getLat))
                .toList();
        List<Barrio> barriosOrdenados = solicitudesPorBarrio.keySet().stream()
                .sorted(Comparator.comparingDouble(b ->
                        calcularDistanciaDesdeOrigen(b.getLat(), b.getLng(), latInicio, lngInicio)))
                .toList();
        List<Solicitud> rutaOptimizada = new ArrayList<>();
        for (Barrio barrio : barriosOrdenados) {
            List<Solicitud> solicitudesBarrio = solicitudesPorBarrio.get(barrio);
            List<Solicitud> solicitudesOrdenadas = solicitudesBarrio.stream()
                    .sorted(Comparator.comparingDouble(Solicitud::getLat))
                    .toList();
            rutaOptimizada.addAll(solicitudesOrdenadas);
        }
        rutaOptimizada.addAll(sinBarrio);
        return rutaOptimizada;
    }

    private double calcularDistanciaDesdeOrigen(double lat, double lng, double latOrigen, double lngOrigen) {
        double deltaLat = lat - latOrigen;
        double deltaLng = lng - lngOrigen;
        return Math.sqrt(deltaLat * deltaLat + deltaLng * deltaLng);
    }

    public Ruta crearRuta(String nombre, List<Solicitud> solicitudes) {
        Ruta ruta = new Ruta(nombre, solicitudes);
        double distancia = calcularDistanciaTotal(solicitudes);
        ruta.setDistanciaTotal(distancia);
        int tiempoViaje = (int) (distancia * 2);
        int tiempoServicio = solicitudes.size() * 5;
        ruta.setTiempoEstimadoMinutos(tiempoViaje + tiempoServicio);
        return ruta;
    }

    public Map<Barrio, List<Solicitud>> agruparPorBarrio(List<Solicitud> solicitudes) {
        return solicitudes.stream()
                .filter(s -> s.getBarrio() != null)
                .collect(Collectors.groupingBy(Solicitud::getBarrio));
    }

    public Map<String, Long> obtenerEstadisticasPorBarrio(List<Solicitud> solicitudes) {
        return solicitudes.stream()
                .filter(s -> s.getBarrio() != null)
                .collect(Collectors.groupingBy(
                        s -> s.getBarrio().getNombre(),
                        Collectors.counting()
                ));
    }
}
