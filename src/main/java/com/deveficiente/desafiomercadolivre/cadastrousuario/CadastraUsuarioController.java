package com.deveficiente.desafiomercadolivre.cadastrousuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CadastraUsuarioController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/usuarios")
	@Transactional
	public String cria(@RequestBody @Valid NovoUsuarioRequest request) {
		Usuario novoUsuario = request.toUsuario();
		manager.persist(novoUsuario);
		return novoUsuario.toString();
	}

}
