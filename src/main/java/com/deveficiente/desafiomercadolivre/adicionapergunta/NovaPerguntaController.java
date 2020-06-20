package com.deveficiente.desafiomercadolivre.adicionapergunta;

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
public class NovaPerguntaController {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	//1
	private UsuarioRepository usuarioRepository;	

	@PostMapping(value = "/produtos/{id}/perguntas")
	@Transactional
	public String cria(@RequestBody @Valid NovaPerguntaRequest request, @PathVariable("id") Long id) {
		Produto produto = manager.find(Produto.class,id);
		Usuario interessada = usuarioRepository.findByEmail("seuemail3@deveficiente.com").get();
		
		Pergunta novaPergunta = request.toModel(interessada,produto);
		manager.persist(novaPergunta);
		
		return novaPergunta.toString();
	}

}
