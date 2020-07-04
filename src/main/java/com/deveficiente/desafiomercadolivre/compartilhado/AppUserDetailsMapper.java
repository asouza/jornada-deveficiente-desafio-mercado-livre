package com.deveficiente.desafiomercadolivre.compartilhado;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;
import com.deveficiente.desafiomercadolivre.compartilhado.seguranca.UserDetailsMapper;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper{

	@Override
	public UserDetails map(Object shouldBeASystemUser) {						
		return new UsuarioLogado((Usuario)shouldBeASystemUser);
	}

}
