package com.sirha.api.repository;

import com.sirha.api.model.Solicitud;
import com.sirha.api.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudRepository extends MongoRepository<Solicitud, String> {

}
