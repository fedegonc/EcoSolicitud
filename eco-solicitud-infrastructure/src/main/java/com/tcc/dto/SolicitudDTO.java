package com.tcc.dto;

public class SolicitudDTO {
    private Long id;
    private String descripcion;
    private double lat;
    private double lng;
    private String usuario;
    private String barrio;
    private boolean completada;

    public SolicitudDTO() {}

    public SolicitudDTO(Long id, String descripcion, double lat, double lng, String usuario, String barrio, boolean completada) {
        this.id = id;
        this.descripcion = descripcion;
        this.lat = lat;
        this.lng = lng;
        this.usuario = usuario;
        this.barrio = barrio;
        this.completada = completada;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLng() { return lng; }
    public void setLng(double lng) { this.lng = lng; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getBarrio() { return barrio; }
    public void setBarrio(String barrio) { this.barrio = barrio; }

    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) { this.completada = completada; }
}
