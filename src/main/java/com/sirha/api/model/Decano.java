package com.sirha.api.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuarios")
public class Decano extends Profesor implements GestorSolicitudes {

    private Facultad carrera;

    public Decano(String nombre, String apellido, String email, String contraseña, Facultad carrera) {
        super(nombre, apellido, email, contraseña);
        this.carrera = carrera;
    }

    public Decano(String nombre, String apellido, String email, String contraseña, Rol rol, Facultad carrera) {
        super(nombre, apellido, email, contraseña, rol);
        this.carrera = carrera;
    }

    @Override
    public void gestionarSolicitud() {
    }
}
