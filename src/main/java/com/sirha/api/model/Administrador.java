package com.sirha.api.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "usuarios")
public class Administrador extends Usuario implements GestorSolicitudes {

    public Administrador(String nombre, String apellido, String email, String password) {
        super(nombre, apellido, email, password);
    }

    public Administrador(String nombre, String apellido, String email, String password, Rol rol) {
        super(nombre, apellido, email, password, rol);
    }

    @Override
    public void gestionarSolicitud() {
        // TODO: Implementar lógica de gestión de solicitudes
    }
}
