package com.deveficiente.desafiomercadolivre.cadastrousuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
		//como esse ponto do codigo sabe que tem que passar a senha limpa?
		return new Usuario(email,new SenhaLimpa(senha));
	}

	public String getEmail() {
		return email;
	}

}
