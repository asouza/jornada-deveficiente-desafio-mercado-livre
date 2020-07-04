package com.deveficiente.desafiomercadolivre.adicionaopiniao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.desafiomercadolivre.cadastroprodutos.Produto;
import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;
import com.deveficiente.desafiomercadolivre.cadastrousuario.UsuarioRepository;

@RestController
public class AdicionaOpiniaoController {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	//1
	private UsuarioRepository usuarioRepository;

	@PostMapping(value = "/api/produtos/{id}/opiniao")
	@Transactional
	//1
	public String adiciona(@RequestBody @Valid NovaOpiniaoRequest request, @PathVariable("id") Long id) {
		//1
		Produto produto = manager.find(Produto.class,id);
		//1
		Usuario consumidor = usuarioRepository.findByEmail("alberto@deveficiente.com").get();
		//1
		Opiniao novaOpiniao =  request.toModel(produto,consumidor);
		manager.persist(novaOpiniao);		
		
		return novaOpiniao.toString();
	}

}
