package com.sirha.api.controller;

import com.sirha.api.dto.SolicitudDTO;
import com.sirha.api.dto.UsuarioDTO;
import com.sirha.api.model.*;
import com.sirha.api.service.EstudianteService;
import com.sirha.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping("/solitud")
    public ResponseEntity crearSolicitud(@Valid @RequestBody SolicitudDTO dto) {
        try {
            Solicitud nuevo = estudianteService.crearSolicitud(dto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/horario/{idEstudiante}/{semestre}")
    public ResponseEntity<?> package com.sirha.api.config;

    import com.sirha.api.model.*;
    import com.sirha.api.repository.UsuarioRepository;
    import org.springframework.boot.CommandLineRunner;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;

    import java.util.Arrays;

    @Configuration
    public class DataSeeder {

        @Bean
        CommandLineRunner seedDatabase(UsuarioRepository usuarioRepository) {
            return args -> {
                // Crear materias
                Materia materia1 = new Materia("MAT101", "Matemáticas I", 4);
                Materia materia2 = new Materia("FIS101", "Física I", 3);

                // Crear registro de materias
                RegistroMaterias registro = new RegistroMaterias();
                registro.setMaterias(Arrays.asList(materia1, materia2));
                registro.setSemestre(1);

                // Crear semestre y agregar registro
                Semestre semestre = new Semestre();
                semestre.setNumero(1);
                semestre.setRegistros(Arrays.asList(registro));

                // Crear estudiante
                Estudiante estudiante = new Estudiante(
                        "Juan",
                        "Pérez",
                        "juan.perez@email.com",
                        "password123",
                        Rol.ESTUDIANTE,
                        Facultad.INGENIERIA
                );
                estudiante.setSemestres(Arrays.asList(semestre));

                // Guardar en la base de datos
                usuarioRepository.save(estudiante);
            };
        }
    }(@PathVariable String idEstudiante , @PathVariable int semestre) {
        try {
            List<RegistroMaterias> registroMaterias = estudianteService.consultarHorarioBySemester(idEstudiante, semestre);
            if (registroMaterias.size() > 0) {
                return ResponseEntity.ok(registroMaterias);
            } else {
                return new ResponseEntity<>("No se encontro el horario", HttpStatus.NO_CONTENT);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @GetMapping("/semaforo/{idEstudiante}/{semestre}")
    public ResponseEntity<?> consultarSemaforoAcademico(@PathVariable String idEstudiante) {
        try {
            Map<String, Semaforo> semaforo = estudianteService.consultarSemaforoAcademico(idEstudiante);
            if (!semaforo.isEmpty()) {
                return ResponseEntity.ok(estudianteService.consultarSemaforoAcademico(idEstudiante));
            } else {
                return new ResponseEntity<>("No se encontro el horario", HttpStatus.NO_CONTENT);
            }
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

}
