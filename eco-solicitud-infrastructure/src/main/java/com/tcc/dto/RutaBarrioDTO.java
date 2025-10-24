package com.tcc.dto;

import java.util.List;

public class RutaBarrioDTO {
    private String barrio;
    private List<SolicitudDTO> solicitudes;

    public RutaBarrioDTO() {}

    public RutaBarrioDTO(String barrio, List<SolicitudDTO> solicitudes) {
        this.barrio = barrio;
        this.solicitudes = solicitudes;
    }

    public String getBarrio() { return barrio; }
    public void setBarrio(String barrio) { this.barrio = barrio; }

    public List<SolicitudDTO> getSolicitudes() { return solicitudes; }
    public void setSolicitudes(List<SolicitudDTO> solicitudes) { this.solicitudes = solicitudes; }
}
