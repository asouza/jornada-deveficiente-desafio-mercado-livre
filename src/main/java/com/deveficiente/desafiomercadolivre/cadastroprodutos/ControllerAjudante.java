package com.deveficiente.desafiomercadolivre.cadastroprodutos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerAjudante {
	@PersistenceContext 
	private EntityManager manager;

	@GetMapping(value = "/todos-produtos")
	public String lista() {
		return manager.createQuery("select p from Produto p").getResultList().toString();
	}
	
	@GetMapping(value = "/todos-usuarios")
	public String listaUsuarios() {
		return manager.createQuery("select u from Usuario u").getResultList().toString();
	}

}
