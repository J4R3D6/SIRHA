package com.sirha.api.service;

import com.sirha.api.dto.SolicitudDTO;
import com.sirha.api.model.Estudiante;
import com.sirha.api.model.Rol;
import com.sirha.api.model.Solicitud;
import com.sirha.api.model.TipoSolicitud;
import com.sirha.api.repository.SolicitudRepository;
import com.sirha.api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Solicitud crearSolicitud(@Valid SolicitudDTO dto) {
        Optional<Estudiante> estudianteOpt = usuarioRepository.findById(dto.getEstudianteId())
                .filter(Estudiante.class::isInstance)
                .map(Estudiante.class::cast);

        if (estudianteOpt.isEmpty()) {
            throw new IllegalArgumentException("El estudiante con ID " + dto.getEstudianteId() + " no existe");
        }
        Solicitud solicitud = new Solicitud();
        solicitud.setObservaciones(dto.getObservaciones());
        solicitud.setEstudianteId(dto.getEstudianteId());
        solicitud.setGrupoProblematicoId(dto.getGrupoProblematicoId());
        solicitud.setGrupoCambioId(dto.getGrupoCambioId());
        TipoSolicitud tipo;
        try {
            tipo = TipoSolicitud.valueOf(dto.getTipoSolicitud());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("tipo no válido: " + dto.getTipoSolicitud() +
                    ". INSCRIPCION_GRUPO, CAMBIO_GRUPO, CANCELACION_GRUPO");
        }
        solicitud.setTipoSolicitud(tipo);
        Solicitud nuevaSolicitud = solicitudRepository.insert(solicitud);
        Estudiante estudiante = estudianteOpt.get();
        estudiante.addSolicitud(nuevaSolicitud);
        usuarioRepository.save(estudiante);
        return nuevaSolicitud;
    }
}
