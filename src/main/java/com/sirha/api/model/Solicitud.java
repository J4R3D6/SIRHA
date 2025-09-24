package com.sirha.api.model;

	import org.springframework.data.annotation.Id;
	import org.springframework.data.mongodb.core.mapping.Document;

	import java.time.LocalDateTime;

	@Document(collection = "solicitudes")
	public class Solicitud {

	    @Id
	    private String id;

	    private Estudiante estudiante;

	    private TipoSolicitud tipoSolicitud;

	    private Grupo grupoProblema;
	    private Materia materiaProblema;

	    private Grupo grupoDestino;
	    private Materia materiaDestino;

	    private String observaciones;

	    private LocalDateTime fechaCreacion;
	    private LocalDateTime fechaResolucion;

	    private String estado; // PENDIENTE, APROBADA, RECHAZADA

	    private String comentariosAdmin;

	    // Getters and setters
	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public Estudiante getEstudiante() {
	        return estudiante;
	    }

	    public void setEstudiante(Estudiante estudiante) {
	        this.estudiante = estudiante;
	    }

	    public TipoSolicitud getTipoSolicitud() {
	        return tipoSolicitud;
	    }

	    public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
	        this.tipoSolicitud = tipoSolicitud;
	    }

	    public Grupo getGrupoProblema() {
	        return grupoProblema;
	    }

	    public void setGrupoProblema(Grupo grupoProblema) {
	        this.grupoProblema = grupoProblema;
	    }

	    public Materia getMateriaProblema() {
	        return materiaProblema;
	    }

	    public void setMateriaProblema(Materia materiaProblema) {
	        this.materiaProblema = materiaProblema;
	    }

	    public Grupo getGrupoDestino() {
	        return grupoDestino;
	    }

	    public void setGrupoDestino(Grupo grupoDestino) {
	        this.grupoDestino = grupoDestino;
	    }

	    public Materia getMateriaDestino() {
	        return materiaDestino;
	    }

	    public void setMateriaDestino(Materia materiaDestino) {
	        this.materiaDestino = materiaDestino;
	    }

	    public String getObservaciones() {
	        return observaciones;
	    }

	    public void setObservaciones(String observaciones) {
	        this.observaciones = observaciones;
	    }

	    public LocalDateTime getFechaCreacion() {
	        return fechaCreacion;
	    }

	    public void setFechaCreacion(LocalDateTime fechaCreacion) {
	        this.fechaCreacion = fechaCreacion;
	    }

	    public LocalDateTime getFechaResolucion() {
	        return fechaResolucion;
	    }

	    public void setFechaResolucion(LocalDateTime fechaResolucion) {
	        this.fechaResolucion = fechaResolucion;
	    }

	    public String getEstado() {
	        return estado;
	    }

	    public void setEstado(String estado) {
	        this.estado = estado;
	    }

	    public String getComentariosAdmin() {
	        return comentariosAdmin;
	    }

	    public void setComentariosAdmin(String comentariosAdmin) {
	        this.comentariosAdmin = comentariosAdmin;
	    }
	}