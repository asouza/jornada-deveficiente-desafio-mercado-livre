package com.deveficiente.desafiomercadolivre.cadastrousuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class NovoUsuarioRequest {

	@Email
	@NotBlank
	private String email;
	@NotBlank
	@Length(min = 6)
	private String senha;

	public NovoUsuarioRequest(@Email @NotBlank String email,
			@NotBlank @Length(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public Usuario toUsuario() {
		return new Usuario(email,senha);
	}

}
