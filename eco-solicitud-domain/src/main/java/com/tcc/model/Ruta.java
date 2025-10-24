package com.tcc.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Transient
    private List<Solicitud> solicitudes;
    private double distanciaTotal;
    private int tiempoEstimadoMinutos;

    public Ruta(String nombre, List<Solicitud> solicitudes) {
        this.nombre = nombre;
        this.solicitudes = new ArrayList<>(solicitudes);
        this.distanciaTotal = 0.0;
        this.tiempoEstimadoMinutos = 0;
    }

    public Ruta() { this.solicitudes = new ArrayList<>(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<Solicitud> getSolicitudes() { return solicitudes; }
    public void setSolicitudes(List<Solicitud> solicitudes) { this.solicitudes = solicitudes; }

    public double getDistanciaTotal() { return distanciaTotal; }
    public void setDistanciaTotal(double distanciaTotal) { this.distanciaTotal = distanciaTotal; }

    public int getTiempoEstimadoMinutos() { return tiempoEstimadoMinutos; }
    public void setTiempoEstimadoMinutos(int tiempoEstimadoMinutos) { this.tiempoEstimadoMinutos = tiempoEstimadoMinutos; }

    @Override
    public String toString() {
        return "Ruta{" +
                "nombre='" + nombre + '\'' +
                ", solicitudes=" + (solicitudes != null ? solicitudes.size() : 0) +
                ", distanciaTotal=" + distanciaTotal +
                ", tiempoEstimadoMinutos=" + tiempoEstimadoMinutos +
                '}';
    }
}
