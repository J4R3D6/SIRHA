package com.sirha.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SolicitudDTO {

    @NotNull(message = "Las observaciones no pueden ser nulas")
    @NotBlank(message = "Las observaciones no pueden estar vacías")
    private String observaciones;

    @NotBlank(message = "El ID del estudiante no puede estar vacío")
    @NotNull(message = "El ID del estudiante no puede ser nulo")
    private String estudianteId;

    @NotBlank(message = "El tipo de solicitud no puede estar vacío")
    @NotNull(message = "El tipo de solicitud no puede ser nulo")
    private String tipoSolicitud;

    @NotBlank(message = "El ID del grupo problemático no puede estar vacío")
    @NotNull(message = "El ID del grupo problemático no puede ser nulo")
    private String grupoProblematicoId;

    private String grupoCambioId;


    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public String getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(String estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getGrupoProblematicoId() {
        return grupoProblematicoId;
    }

    public void setGrupoProblematicoId(String grupoProblematicoId) {
        this.grupoProblematicoId = grupoProblematicoId;
    }

    public String getGrupoCambioId() {
        return grupoCambioId;
    }

    public void setGrupoCambioId(String grupoCambioId) {
        this.grupoCambioId = grupoCambioId;
    }
}
