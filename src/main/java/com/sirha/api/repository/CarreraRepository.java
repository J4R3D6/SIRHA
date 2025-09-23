package com.sirha.api.repository;

import com.sirha.api.model.Carrera;
import com.sirha.api.model.Facultad;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Carrera.
 * Permite operaciones CRUD sobre la colección 'carreras' en MongoDB.
 */
@Repository
public interface CarreraRepository extends MongoRepository<Carrera, String> {

    List<Carrera> findByNombre(Facultad nombre);
    Carrera findByCodigo(String codigo);
}