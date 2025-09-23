package com.sirha.api.model;

public class RegistroMaterias {

	private Semaforo estado;

	private Materia materia;

	public Semaforo getEstado() {
		return estado;
	}

	public void setEstado(Semaforo estado) {
		this.estado = estado;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
		this.estado = Semaforo.AZUL;
	}

}
