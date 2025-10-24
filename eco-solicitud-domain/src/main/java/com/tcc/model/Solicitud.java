package com.tcc.model;

import jakarta.persistence.*;

@Entity
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private double lat;
    private double lng;
    private String entidadRecolectora;
    private Long organizacionId;
    private Double distancia;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado = EstadoSolicitud.RECIBIDA;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "barrio_id")
    private Barrio barrio;

    public Solicitud() {}

    public Solicitud(String descripcion, double lat, double lng) {
        this.descripcion = descripcion;
        this.lat = lat;
        this.lng = lng;
        this.estado = EstadoSolicitud.RECIBIDA;
    }

    public Solicitud(String descripcion, double lat, double lng, String entidadRecolectora) {
        this.descripcion = descripcion;
        this.lat = lat;
        this.lng = lng;
        this.entidadRecolectora = entidadRecolectora;
        this.estado = EstadoSolicitud.RECIBIDA;
    }

    public Solicitud(String descripcion, double lat, double lng, Barrio barrio) {
        this.descripcion = descripcion;
        this.lat = lat;
        this.lng = lng;
        this.barrio = barrio;
        this.estado = EstadoSolicitud.RECIBIDA;
    }

    public Solicitud(String descripcion, double lat, double lng, Usuario usuario, Barrio barrio) {
        this.descripcion = descripcion;
        this.lat = lat;
        this.lng = lng;
        this.usuario = usuario;
        this.barrio = barrio;
        this.estado = EstadoSolicitud.RECIBIDA;
    }

    public Solicitud(String descripcion, double lat, double lng, Usuario usuario, Barrio barrio, EstadoSolicitud estado) {
        this.descripcion = descripcion;
        this.lat = lat;
        this.lng = lng;
        this.usuario = usuario;
        this.barrio = barrio;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLng() { return lng; }
    public void setLng(double lng) { this.lng = lng; }

    public EstadoSolicitud getEstado() { return estado; }
    public void setEstado(EstadoSolicitud estado) { this.estado = estado; }

    public boolean estaCompletada() { return EstadoSolicitud.COMPLETADA.equals(this.estado); }
    public boolean isCompletada() { return estaCompletada(); }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Barrio getBarrio() { return barrio; }
    public void setBarrio(Barrio barrio) { this.barrio = barrio; }

    public String getEntidadRecolectora() { return entidadRecolectora; }
    public void setEntidadRecolectora(String entidadRecolectora) { this.entidadRecolectora = entidadRecolectora; }

    public Long getOrganizacionId() { return organizacionId; }
    public void setOrganizacionId(Long organizacionId) { this.organizacionId = organizacionId; }

    public Double getDistancia() { return distancia; }
    public void setDistancia(Double distancia) { this.distancia = distancia; }

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", estado=" + estado +
                ", barrio=" + (barrio != null ? barrio.getNombre() : "sin barrio") +
                ", usuario=" + (usuario != null ? usuario.getNombre() : "sin usuario") +
                '}';
    }
}
