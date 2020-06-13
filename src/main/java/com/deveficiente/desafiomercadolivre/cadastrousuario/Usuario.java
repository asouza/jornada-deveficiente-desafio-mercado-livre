package com.deveficiente.desafiomercadolivre.cadastrousuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Email @NotBlank String email;
	private @NotBlank @Length(min = 6) String senha;

	public Usuario(@Email @NotBlank String email,
			@Valid @NotNull SenhaLimpa senhaLimpa) {
		Assert.isTrue(StringUtils.hasLength(email),"email não pode ser em branco");
		Assert.notNull(senhaLimpa,"o objeto do tipo senha limpa nao pode ser nulo");	
		
		this.email = email;
		this.senha = senhaLimpa.hash();
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha
				+ "]";
	}

}
