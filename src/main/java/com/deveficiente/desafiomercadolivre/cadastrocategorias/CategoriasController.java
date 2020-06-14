package com.deveficiente.desafiomercadolivre.cadastrocategorias;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriasController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/categorias")
	@Transactional
	public String postMethodName(@RequestBody @Valid NovaCategoriaRequest request) {
		Categoria categoria = request.toModel(manager);
		manager.persist(categoria);
		return categoria.toString();
	}

}
