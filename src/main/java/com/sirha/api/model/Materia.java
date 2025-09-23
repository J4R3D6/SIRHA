package com.sirha.api.model;

public class Materia {

	private String nombre;

	private String acronimo;

	private int creditos;

	public Materia() {
	}

	public Materia(String nombre, String acronimo, int creditos) {
		this.nombre = nombre;
		this.acronimo = acronimo;
		this.creditos = creditos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAcronimo() {
		return acronimo;
	}

	public void setAcronimo(String acronimo) {
		this.acronimo = acronimo;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
}
