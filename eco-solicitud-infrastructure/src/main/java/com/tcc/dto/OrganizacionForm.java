package com.tcc.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizacionForm {
    @NotBlank(message = "{org.nombre.required}")
    private String nombre;
    @NotBlank(message = "{org.direccion.required}")
    private String direccion;
    @NotBlank(message = "{org.telefono.required}")
    private String telefono;
    private Double lat;
    private Double lng;
}
