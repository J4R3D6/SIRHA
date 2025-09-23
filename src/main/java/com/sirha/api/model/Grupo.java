package com.sirha.api.model;

import java.util.ArrayList;
import java.util.List;

public class Grupo {

	private String id;

	private int capacidad = 0;

	private int cantidadInscritos;

	private boolean estaCompleto;

	private List<Horario> horarios = new ArrayList<>();

	private List<String> estudiantesId = new ArrayList<String>();

	private Materia materia;

	public Grupo() {
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public List<String> getEstudiantesId() {
		return estudiantesId;
	}

	public void setEstudiantesId(List<String> estudiantesId) {
		this.estudiantesId = estudiantesId;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

	public boolean isEstaCompleto() {
		return estaCompleto;
	}

	public void setEstaCompleto(boolean estaCompleto) {
		this.estaCompleto = estaCompleto;
	}

	public int getCantidadInscritos() {
		return cantidadInscritos;
	}

	public void setCantidadInscritos(int cantidadInscritos) {
		this.cantidadInscritos = cantidadInscritos;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
