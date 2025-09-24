package com.sirha.api.service;

import com.sirha.api.dto.SolicitudDTO;
import com.sirha.api.model.*;
import com.sirha.api.repository.SolicitudRepository;
import com.sirha.api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EstudianteService {

    private final SolicitudRepository solicitudRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public EstudianteService(SolicitudRepository solicitudRepository, UsuarioRepository usuarioRepository) {
        this.solicitudRepository = solicitudRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<RegistroMaterias> consultarHorarioBySemester(String idEstudiante, int semestre) {
        Optional<Estudiante> estudianteOpt = usuarioRepository.findById(idEstudiante)
                .filter(Estudiante.class::isInstance)
                .map(Estudiante.class::cast);
        if (estudianteOpt.isEmpty()) {
            throw new IllegalArgumentException("El estudiante con ID " + idEstudiante + " no existe");
        }
        try {
            Estudiante estudiante = estudianteOpt.get();
            List<RegistroMaterias> registroMaterias = estudiante.getRegistrosBySemestre(semestre);
            if (registroMaterias.isEmpty()) {
                throw new IllegalArgumentException("No se encontraron registros para el semestre: " + semestre);
            }
            return registroMaterias;
        }catch (Exception e) {
            throw new IllegalArgumentException("Semestre no válido " + semestre);
        }
    }

    public Map<String, Semaforo> consultarSemaforoAcademico(String idEstudiante) {
        Optional<Estudiante> estudianteOpt = usuarioRepository.findById(idEstudiante)
                .filter(Estudiante.class::isInstance)
                .map(Estudiante.class::cast);
        if (estudianteOpt.isEmpty()) {
            throw new IllegalArgumentException("El estudiante con ID " + idEstudiante + " no existe");
        }
        Estudiante estudiante = estudianteOpt.get();
        return estudiante.getSemaforo();
    }
}
