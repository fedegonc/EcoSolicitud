package com.tcc.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Barrio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private double lat;
    private double lng;

    @OneToMany(mappedBy = "barrio")
    private List<Solicitud> solicitudes = new ArrayList<>();

    public Barrio() {}

    public Barrio(String nombre, double lat, double lng) {
        this.nombre = nombre;
        this.lat = lat;
        this.lng = lng;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLng() { return lng; }
    public void setLng(double lng) { this.lng = lng; }

    public List<Solicitud> getSolicitudes() { return solicitudes; }
    public void setSolicitudes(List<Solicitud> solicitudes) { this.solicitudes = solicitudes; }

    @Override
    public String toString() {
        return "Barrio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
