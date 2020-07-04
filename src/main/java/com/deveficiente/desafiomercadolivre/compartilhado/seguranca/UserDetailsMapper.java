package com.deveficiente.desafiomercadolivre.compartilhado.seguranca;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Interface que você pode implementar para retornar um {@link UserDetails}
 * em função do objeto que representa sua entidade de usuário
 * @author albertoluizsouza
 *
 */
public interface UserDetailsMapper {

	/**
	 * 
	 * @param shouldBeASystemUser um objeto que deveria representar seu usuário logado
	 * @return
	 */
	UserDetails map(Object shouldBeASystemUser);
}
