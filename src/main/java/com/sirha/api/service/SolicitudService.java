package com.sirha.api.service;

import com.sirha.api.dto.SolicitudDTO;
import com.sirha.api.model.*;
import com.sirha.api.repository.GrupoRepository;
import com.sirha.api.repository.MateriaRepository;
import com.sirha.api.repository.SolicitudRepository;
import com.sirha.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;
    private final MateriaRepository materiaRepository;

    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository,
                           UsuarioRepository usuarioRepository,
                           GrupoRepository grupoRepository,
                           MateriaRepository materiaRepository) {
        this.solicitudRepository = solicitudRepository;
        this.usuarioRepository = usuarioRepository;
        this.grupoRepository = grupoRepository;
        this.materiaRepository = materiaRepository;
    }

    public Solicitud crearSolicitud(SolicitudDTO solicitudDTO) {
        // 1. Verificar que el estudiante existe
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(solicitudDTO.getEstudianteId());
        if (usuarioOpt.isEmpty() || !(usuarioOpt.get() instanceof Estudiante)) {
            throw new IllegalArgumentException("El estudiante con ID " + solicitudDTO.getEstudianteId() + " no existe o no es un estudiante");
        }
        Estudiante estudiante = (Estudiante) usuarioOpt.get();

        // 2. Verificar grupo y materia de origen
        Optional<Grupo> grupoProblemaOpt = grupoRepository.findById(solicitudDTO.getGrupoProblemaId());
        if (grupoProblemaOpt.isEmpty()) {
            throw new IllegalArgumentException("El grupo con ID " + solicitudDTO.getGrupoProblemaId() + " no existe");
        }
        Grupo grupoProblema = grupoProblemaOpt.get();

        Optional<Materia> materiaProblemaOpt = materiaRepository.findByAcronimo(solicitudDTO.getMateriaProblemaAcronimo());
        if (materiaProblemaOpt.isEmpty()) {
            throw new IllegalArgumentException("La materia con acrónimo " + solicitudDTO.getMateriaProblemaAcronimo() + " no existe");
        }
        Materia materiaProblema = materiaProblemaOpt.get();

        // 3. Verificar que la materia corresponde al grupo
        if (!grupoProblema.getMateria().getId().equals(materiaProblema.getId())) {
            throw new IllegalArgumentException("La materia indicada no corresponde al grupo con problemas");
        }

        // 4. Verificar que el estudiante está inscrito en el grupo
        if (!grupoProblema.getEstudiantesId().contains(solicitudDTO.getEstudianteId())) {
            throw new IllegalArgumentException("El estudiante no está inscrito en el grupo indicado");
        }

        // 5. Si es CAMBIO_GRUPO, verificar grupo y materia destino
        Grupo grupoDestino = null;
        Materia materiaDestino = null;

        if (solicitudDTO.getTipoSolicitud() == TipoSolicitud.CAMBIO_GRUPO) {
            if (solicitudDTO.getGrupoDestinoId() == null || solicitudDTO.getMateriaDestinoAcronimo() == null) {
                throw new IllegalArgumentException("Para cambio de grupo se requiere especificar el grupo y materia destino");
            }

            Optional<Grupo> grupoDestinoOpt = grupoRepository.findById(solicitudDTO.getGrupoDestinoId());
            if (grupoDestinoOpt.isEmpty()) {
                throw new IllegalArgumentException("El grupo destino con ID " + solicitudDTO.getGrupoDestinoId() + " no existe");
            }
            grupoDestino = grupoDestinoOpt.get();

            Optional<Materia> materiaDestinoOpt = materiaRepository.findByAcronimo(solicitudDTO.getMateriaDestinoAcronimo());
            if (materiaDestinoOpt.isEmpty()) {
                throw new IllegalArgumentException("La materia destino con acrónimo " + solicitudDTO.getMateriaDestinoAcronimo() + " no existe");
            }
            materiaDestino = materiaDestinoOpt.get();

            // Verificar que la materia destino corresponde al grupo destino
            if (!grupoDestino.getMateria().getId().equals(materiaDestino.getId())) {
                throw new IllegalArgumentException("La materia destino no corresponde al grupo destino");
            }

            // Verificar que el grupo destino no esté lleno
            if (grupoDestino.isEstaCompleto()) {
                throw new IllegalArgumentException("El grupo destino está completo");
            }
        }

        // 6. Crear la solicitud
        Solicitud solicitud = new Solicitud();
        solicitud.setEstudiante(estudiante);
        solicitud.setTipoSolicitud(solicitudDTO.getTipoSolicitud());
        solicitud.setGrupoProblema(grupoProblema);
        solicitud.setMateriaProblema(materiaProblema);

        if (grupoDestino != null && materiaDestino != null) {
            solicitud.setGrupoDestino(grupoDestino);
            solicitud.setMateriaDestino(materiaDestino);
        }

        solicitud.setObservaciones(solicitudDTO.getObservaciones());
        solicitud.setFechaCreacion(LocalDateTime.now());
        solicitud.setEstado("PENDIENTE");

        return solicitudRepository.save(solicitud);
    }

    public List<Solicitud> obtenerSolicitudesPorEstudiante(String estudianteId) {
        // Verify the student exists
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(estudianteId);
        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("El estudiante con ID " + estudianteId + " no existe");
        }

        // Verify the user is a student
        if (!(usuarioOpt.get() instanceof Estudiante)) {
            throw new IllegalArgumentException("El usuario con ID " + estudianteId + " no es un estudiante");
        }

        // Get all solicitudes for this student
        return solicitudRepository.findByEstudiante_Id(estudianteId);
    }
}