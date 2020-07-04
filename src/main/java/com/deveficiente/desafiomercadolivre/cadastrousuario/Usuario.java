package com.deveficiente.desafiomercadolivre.cadastrousuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Email @NotBlank String email;
	private @NotBlank @Length(min = 6) String senha;
	//@NotNull
	@PastOrPresent
	private LocalDateTime instanteCriacao;
	
	@Deprecated
	public Usuario() {

	}

	public Usuario(@Email @NotBlank String email,
			@Valid @NotNull SenhaLimpa senhaLimpa) {
		Assert.isTrue(StringUtils.hasLength(email),"email não pode ser em branco");
		Assert.notNull(senhaLimpa,"o objeto do tipo senha limpa nao pode ser nulo");	
		
		this.email = email;
		this.senha = senhaLimpa.hash();
		this.instanteCriacao = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha
				+ "]";
	}
	
	public String getSenha() {
		return senha;
	}

	public String getEmail() {
		return this.email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public Long getId() {
		return this.id;
	}
	
	

}
