package com.deveficiente.desafiomercadolivre.cadastrousuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Email @NotBlank String email;
	private @NotBlank @Length(min = 6) String senha;

	public Usuario(@Email @NotBlank String email,
			@NotBlank @Length(min = 6) String senha) {
		this.email = email;
		// TODO ainda falta aplicar o hash da senha
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha
				+ "]";
	}

}
