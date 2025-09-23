package com.sirha.api.model;

public class Carrera {

    private Facultad nombre ;
	private Materia[] materias;

    public Carrera() {
    }

    public Carrera(Facultad nombre, Materia[] materias) {
        this.nombre = nombre;
        this.materias = materias;
    }

    public Facultad getNombre() {
        return nombre;
    }

    public void setNombre(Facultad nombre) {
        this.nombre = nombre;
    }

    public Materia[] getMaterias() {
        return materias;
    }

    public void setMaterias(Materia[] materias) {
        this.materias = materias;
    }
}
