package com.sirha.api.model.builder;

import java.util.List;

import com.sirha.api.model.Carrera;
import com.sirha.api.model.Facultad;
import com.sirha.api.model.Materia;

import java.util.List;
import java.util.Map;

public interface CarreraBuilder {
    CarreraBuilder nombre(Facultad nombre);
    CarreraBuilder codigo(String codigo);
    CarreraBuilder duracionSemestres(int duracion);
    CarreraBuilder materias(List<Materia> materias);
    CarreraBuilder materiasPorSemestre(Map<Integer, List<Materia>> materiasPorSemestre);
    CarreraBuilder creditosTotales(int creditos);
    Carrera build();
}