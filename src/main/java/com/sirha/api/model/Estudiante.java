package com.sirha.api.model;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuarios")
public class Estudiante extends Usuario {

	private Facultad carrera;

	private List<Semestre> semestres = new ArrayList<>();

	private List<Grupo> grupos = new ArrayList<>();

	public Estudiante(String nombre, String apellido, String email, String contraseña, Facultad carrera) {
		super(nombre, apellido, email, contraseña);
		this.carrera = carrera;
	}

	public Estudiante(String nombre, String apellido, String email, String contraseña, Rol rol, Facultad carrera) {
		super(nombre, apellido, email, contraseña, rol);
		this.carrera = carrera;
	}
}