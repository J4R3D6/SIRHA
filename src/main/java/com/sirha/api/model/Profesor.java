package com.sirha.api.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "usuarios")
public class Profesor extends Usuario {

    public Profesor(String nombre, String apellido, String email, String password) {
        super(nombre, apellido, email, password);
    }

    public Profesor(String nombre, String apellido, String email, String password, Rol rol) {
        super(nombre, apellido, email, password, rol);
    }
}