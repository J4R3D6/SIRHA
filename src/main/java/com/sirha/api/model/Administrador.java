package com.sirha.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Administrador extends Usuario implements GestorSolicitudes {

    public Administrador() {
        super();
    }

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
