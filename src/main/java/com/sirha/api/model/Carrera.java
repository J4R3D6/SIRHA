package com.sirha.api.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "carreras")
public class Carrera {

    private Facultad nombre; 
    private String codigo; 
    private int duracionSemestres; 
    private Materia[] materias; 
    private Map<Integer, List<Materia>> materiasPorSemestre; 
    private int creditosTotales; 

    public Carrera() {}

    public Carrera(Facultad nombre, String codigo,
                   int duracionSemestres, Materia[] materias,
                   Map<Integer, List<Materia>> materiasPorSemestre,
                   int creditosTotales) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.duracionSemestres = duracionSemestres;
        this.materias = materias;
        this.materiasPorSemestre = materiasPorSemestre;
        this.creditosTotales = creditosTotales;
    }

    // Getters y setters

    public Facultad getNombre() {
        return nombre;
    }

    public void setNombre(Facultad nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getDuracionSemestres() {
        return duracionSemestres;
    }

    public void setDuracionSemestres(int duracionSemestres) {
        this.duracionSemestres = duracionSemestres;
    }

    public Materia[] getMaterias() {
        return materias;
    }

    public void setMaterias(Materia[] materias) {
        this.materias = materias;
    }

    public Map<Integer, List<Materia>> getMateriasPorSemestre() {
        return materiasPorSemestre;
    }

    public void setMateriasPorSemestre(Map<Integer, List<Materia>> materiasPorSemestre) {
        this.materiasPorSemestre = materiasPorSemestre;
    }

    public int getCreditosTotales() {
        return creditosTotales;
    }

    public void setCreditosTotales(int creditosTotales) {
        this.creditosTotales = creditosTotales;
    }


    public List<Materia> getMateriasDelSemestre(int semestre) {
        return materiasPorSemestre != null ? materiasPorSemestre.get(semestre) : null;
    }

    public int getTotalMaterias() {
        return materias != null ? materias.length : 0;
    }

    @Override
    public String toString() {
        return String.format("Carrera{nombre='%s', codigo='%s', duracion=%d semestres, materias=%d, creditos=%d}",
                nombre, codigo, duracionSemestres, getTotalMaterias(), creditosTotales);
    }
}