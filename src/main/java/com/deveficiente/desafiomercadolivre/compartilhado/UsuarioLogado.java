package com.deveficiente.desafiomercadolivre.compartilhado;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;

public class UsuarioLogado implements UserDetails {

	private Usuario usuario;
	private User springUserDetails;

	public UsuarioLogado(@NotNull @Valid Usuario usuario) {
		this.usuario = usuario;
		springUserDetails = new User(usuario.getEmail(), usuario.getSenha(), List.of());
	}
	
	

	public Collection<GrantedAuthority> getAuthorities() {
		return springUserDetails.getAuthorities();
	}



	public String getPassword() {
		return springUserDetails.getPassword();
	}



	public String getUsername() {
		return springUserDetails.getUsername();
	}



	public boolean isEnabled() {
		return springUserDetails.isEnabled();
	}



	public boolean isAccountNonExpired() {
		return springUserDetails.isAccountNonExpired();
	}



	public boolean isAccountNonLocked() {
		return springUserDetails.isAccountNonLocked();
	}



	public boolean isCredentialsNonExpired() {
		return springUserDetails.isCredentialsNonExpired();
	}



	public Usuario get() {
		return usuario;
	}

}
