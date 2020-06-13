package com.deveficiente.desafiomercadolivre.cadastrousuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeUsuarioComEmailDuplicadoValidator implements Validator {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoUsuarioRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		NovoUsuarioRequest request = (NovoUsuarioRequest) target;

		Query consultaEmailUnico = manager.createQuery("select 1 from Usuario u where u.email = :email")
				.setParameter("email", request.getEmail());
		
		List<?> resultList = consultaEmailUnico.getResultList();
		
		if(!resultList.isEmpty()) {
			errors.rejectValue("email",null, "ja existe este email no sistema");
		}
	}

}
