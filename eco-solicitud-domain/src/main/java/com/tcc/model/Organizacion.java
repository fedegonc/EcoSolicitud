package com.tcc.model;

import jakarta.persistence.*;

@Entity
public class Organizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private String telefono;
    private Double lat;
    private Double lng;

    public Organizacion() {}

    public Organizacion(String nombre, String direccion, String telefono, Double lat, Double lng) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.lat = lat;
        this.lng = lng;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Double getLat() { return lat; }
    public void setLat(Double lat) { this.lat = lat; }

    public Double getLng() { return lng; }
    public void setLng(Double lng) { this.lng = lng; }
}
