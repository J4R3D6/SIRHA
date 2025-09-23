package com.sirha.api.config;

import com.sirha.api.model.Carrera;
import com.sirha.api.model.Facultad;
import com.sirha.api.model.Materia;
import com.sirha.api.model.builder.ImpCarreraBuilder;
import com.sirha.api.repository.CarreraRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataSeederCarrera implements CommandLineRunner {

    private final CarreraRepository carreraRepository;

    public DataSeederCarrera(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Limpia datos previos
        carreraRepository.deleteAll();

        // Materias de ejemplo
        Materia algoritmos = new Materia("Algoritmos", "ALG", 3);
        Materia matematicas = new Materia("Matemáticas", "MAT", 4);
        Materia programacion = new Materia("Programación", "PROG", 3);

        Materia micro = new Materia("Microeconomía", "MIC", 4);
        Materia macro = new Materia("Macroeconomía", "MAC", 4);

        // Ingeniería de Sistemas
        List<Materia> materiasIS = Arrays.asList(algoritmos, matematicas, programacion);
        Map<Integer, List<Materia>> materiasPorSemIS = new HashMap<>();
        materiasPorSemIS.put(1, Arrays.asList(algoritmos, matematicas));
        materiasPorSemIS.put(2, Arrays.asList(programacion));

        Carrera ingenieriaSistemas = new ImpCarreraBuilder()
                .nombre(Facultad.INGENIERIA_SISTEMAS)
                .codigo("IS001")
                .duracionSemestres(10)
                .materias(materiasIS)
                .materiasPorSemestre(materiasPorSemIS)
                .creditosTotales(140)
                .build();

        // Economía
        List<Materia> materiasECO = Arrays.asList(micro, macro);
        Map<Integer, List<Materia>> materiasPorSemECO = new HashMap<>();
        materiasPorSemECO.put(1, Arrays.asList(micro));
        materiasPorSemECO.put(2, Arrays.asList(macro));

        Carrera economia = new ImpCarreraBuilder()
                .nombre(Facultad.ADMINISTRACION)
                .codigo("ECO001")
                .duracionSemestres(8)
                .materias(materiasECO)
                .materiasPorSemestre(materiasPorSemECO)
                .creditosTotales(120)
                .build();

        // Ingeniería Civil
        Materia estructuras = new Materia("Estructuras", "EST", 5);
        Materia materiales = new Materia("Materiales de Construcción", "MATC", 4);
        List<Materia> materiasICIVIL = Arrays.asList(estructuras, materiales);
        Map<Integer, List<Materia>> materiasPorSemICIVIL = new HashMap<>();
        materiasPorSemICIVIL.put(1, Arrays.asList(estructuras));
        materiasPorSemICIVIL.put(2, Arrays.asList(materiales));

        Carrera ingenieriaCivil = new ImpCarreraBuilder()
                .nombre(Facultad.INGENIERIA_CIVIL)
                .codigo("IC001")
                .duracionSemestres(10)
                .materias(materiasICIVIL)
                .materiasPorSemestre(materiasPorSemICIVIL)
                .creditosTotales(150)
                .build();

        // Guarda carreras en MongoDB
        carreraRepository.saveAll(Arrays.asList(ingenieriaSistemas, economia, ingenieriaCivil));

        System.out.println("=== Carreras iniciales cargadas en la base de datos ===");
    }
}