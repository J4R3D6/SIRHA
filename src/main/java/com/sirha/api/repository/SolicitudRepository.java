package com.sirha.api.repository;

import com.sirha.api.model.Solicitud;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends MongoRepository<Solicitud, String> {
    List<Solicitud> findByEstudiante_Id(String estudianteId);
    List<Solicitud> findByEstado(String estado);
}