package com.tcc.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data; // Importación clave de Lombok
import lombok.NoArgsConstructor; // Constructor sin argumentos (necesario para Spring/JPA/Jackson)

@Data // Genera Getters, Setters, toString(), equals(), y hashCode()
@NoArgsConstructor // Genera el constructor por defecto
public class SolicitudForm {

    // Se recomienda usar final en los mensajes para que sean inmutables
    private static final String LAT_RANGE = "{solicitud.form.lat.range}";
    private static final String LNG_RANGE = "{solicitud.form.lng.range}";

    @NotBlank(message = "{solicitud.form.descripcion.required}")
    private String descripcion;

    @NotNull(message = "{solicitud.form.lat.required}")
    // Validaciones de latitud: entre -90.0 y 90.0
    @DecimalMin(value = "-90.0", message = LAT_RANGE)
    @DecimalMax(value = "90.0", message = LAT_RANGE)
    private Double lat;

    @NotNull(message = "{solicitud.form.lng.required}")
    // Validaciones de longitud: entre -180.0 y 180.0
    @DecimalMin(value = "-180.0", message = LNG_RANGE)
    @DecimalMax(value = "180.0", message = LNG_RANGE)
    private Double lng;
    
    @NotNull(message = "{solicitud.form.organizacion.required}")
    private Long organizacionId;
    
    // **¡No se necesitan getters, setters ni constructor aquí!**
    // Lombok se encarga de todo con las anotaciones @Data y @NoArgsConstructor.
}