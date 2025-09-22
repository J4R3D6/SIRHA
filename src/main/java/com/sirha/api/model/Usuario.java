package com.sirha.api.model;

import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "usuarios")
public abstract class Usuario {

	@Id
	private String id;

	@NotNull
	@NotBlank
	private String nombre;

	@NotNull
	@NotBlank
	private String apellido;

	@Indexed(unique = true)
	@NotBlank
	@NotNull
	private String email;

	@NotNull
	@NotBlank
	private String contraseña;

	@NotNull
	@NotBlank
	private Rol rol;

	public Usuario(String nombre, String apellido, String email, String contraseña) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contraseña = contraseña;
	}

	public Usuario(String nombre, String apellido, String email, String contraseña, Rol rol) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contraseña = contraseña;
		this.rol = rol;
	}

}
