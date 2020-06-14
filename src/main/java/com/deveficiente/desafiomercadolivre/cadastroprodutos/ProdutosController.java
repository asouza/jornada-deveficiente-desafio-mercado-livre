package com.deveficiente.desafiomercadolivre.cadastroprodutos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;
import com.deveficiente.desafiomercadolivre.cadastrousuario.UsuarioRepository;

@RestController
public class ProdutosController {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping(value = "/produtos")
	public String cria(@RequestBody @Valid NovoProdutoRequest request) {
		//simulando o usuario logado
		Usuario dono = usuarioRepository.findByEmail("alberto@deveficiente.com").get();		
		Produto produto = request.toModel(manager,dono);
		
		return produto.toString();
	}

}
