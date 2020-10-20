package com.deveficiente.desafiomercadolivre.cadastrousuario;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeUsuarioComEmailDuplicadoValidator implements Validator {

	//1
	private UsuarioRepository usuarioRepository;
	
	public ProibeUsuarioComEmailDuplicadoValidator(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		//1
		return NovoUsuarioRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//2
		if (errors.hasErrors()) {
			return;
		}

		NovoUsuarioRequest request = (NovoUsuarioRequest) target;

		Optional<Usuario> possivelUsuario = usuarioRepository.findByEmail(request.getEmail());
		
		//3
		if(possivelUsuario.isPresent()) {
			errors.rejectValue("email",null, "ja existe este email no sistema");
		}
	}

}
