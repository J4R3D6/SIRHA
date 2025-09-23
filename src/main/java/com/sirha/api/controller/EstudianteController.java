package com.sirha.api.controller;

import com.sirha.api.dto.SolicitudDTO;
import com.sirha.api.dto.UsuarioDTO;
import com.sirha.api.model.Solicitud;
import com.sirha.api.model.Usuario;
import com.sirha.api.service.EstudianteService;
import com.sirha.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping("/register")
    public ResponseEntity crearSolicitud(@Valid @RequestBody SolicitudDTO dto) {
        try {
            Solicitud nuevo = estudianteService.crearSolicitud(dto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
