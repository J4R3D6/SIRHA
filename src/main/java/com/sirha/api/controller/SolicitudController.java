package com.sirha.api.controller;

import com.sirha.api.dto.SolicitudDTO;
import com.sirha.api.model.Solicitud;
import com.sirha.api.service.SolicitudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudiantes/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    @Autowired
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    public ResponseEntity<?> crearSolicitud(@Valid @RequestBody SolicitudDTO solicitudDTO) {
        try {
            Solicitud solicitudCreada = solicitudService.crearSolicitud(solicitudDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(solicitudCreada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    @GetMapping("/{estudianteId}")
    public ResponseEntity<?> obtenerSolicitudesEstudiante(@PathVariable String estudianteId) {
        try {
            return ResponseEntity.ok(solicitudService.obtenerSolicitudesPorEstudiante(estudianteId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las solicitudes: " + e.getMessage());
        }
    }
}