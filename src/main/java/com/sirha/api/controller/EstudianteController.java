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
    public ResponseEntity<?> consultarHorarioPorSemestre(@PathVariable String idEstudiante, @PathVariable int semestre) {
        try {
            List<RegistroMaterias> registroMaterias = estudianteService.consultarHorarioBySemester(idEstudiante, semestre);
            if (registroMaterias.isEmpty()) {
                return new ResponseEntity<>("No se encontro el horario", HttpStatus.NO_CONTENT);
            }

            Map<String, Horario> horarioPorAcronimo = new HashMap<>();
            for (RegistroMaterias registro : registroMaterias) {
                Grupo grupo = registro.getGrupo();
                if (grupo != null && grupo.getHorarios() != null && !grupo.getHorarios().isEmpty()) {
                    String acronimo = grupo.getMateria().getAcronimo();
                    Horario horario = grupo.getHorarios().get(0); // Primer horario
                    horarioPorAcronimo.put(acronimo, horario);
                }
            }
            return ResponseEntity.ok(horarioPorAcronimo);
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
