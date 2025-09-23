package com.sirha.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;


@Document(collection = "solicitudes")
public class Solicitud {

	@Id
	@Indexed(unique = true)
	private String radicado;

	@NotNull
	@NotBlank
	private String observaciones;


	@CreatedDate
	private LocalDateTime fechaCreacion;

	@LastModifiedDate
	private LocalDateTime fechaActualizacion;

	@NotNull
	@NotBlank
	private SolicitudEstado estado;

	@NotNull
	@NotBlank
	private TipoSolicitud tipoSolicitud;

	@NotNull
	@NotBlank
	private String estudianteId;

	@NotNull
	@NotBlank
	private String grupoProblematicoId;

	private String grupoCambioId;

	public Solicitud() {
	}

	public Solicitud(String observaciones, TipoSolicitud tipoSolicitud,
					 String estudianteId, String grupoProblematicoId, String grupoCambioId) {
		this.observaciones = observaciones;
		this.estado = SolicitudEstado.PENDIENTE;
		this.tipoSolicitud = tipoSolicitud;
		this.estudianteId = estudianteId;
		this.grupoProblematicoId = grupoProblematicoId;
		this.grupoCambioId = grupoCambioId;
	}

	public Solicitud( String observaciones,SolicitudEstado estado, TipoSolicitud tipoSolicitud, String estudianteId, String grupoProblematicoId, String grupoCambioId) {
		this.estado = estado;
		this.observaciones = observaciones;
		this.tipoSolicitud = tipoSolicitud;
		this.estudianteId = estudianteId;
		this.grupoProblematicoId = grupoProblematicoId;
		this.grupoCambioId = grupoCambioId;
	}

	public String getRadicado() {
		return radicado;
	}

	public void setRadicado(String radicado) {
		this.radicado = radicado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public SolicitudEstado getEstado() {
		return estado;
	}

	public void setEstado(SolicitudEstado estado) {
		this.estado = estado;
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

	public TipoSolicitud getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

}
