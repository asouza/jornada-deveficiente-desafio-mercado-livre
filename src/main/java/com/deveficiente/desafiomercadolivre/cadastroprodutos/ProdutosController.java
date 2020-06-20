package com.deveficiente.desafiomercadolivre.cadastroprodutos;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
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
	//1
	private UsuarioRepository usuarioRepository;
	@Autowired
	//1
	private Uploader uploaderFake;
	
	@InitBinder(value = "novoProdutoRequest")
	public void init(WebDataBinder webDataBinder) {
		//1
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}

	@PostMapping(value = "/produtos")
	@Transactional
	//1
	public String cria(@RequestBody @Valid NovoProdutoRequest request) {
		//simulando o usuario logado
		//1
		Usuario dono = usuarioRepository.findByEmail("alberto@deveficiente.com").get();		
		//1
		Produto produto = request.toModel(manager,dono);
		
		manager.persist(produto);
		return produto.toString();
	}
	
	@PostMapping(value = "/produtos/{id}/imagens")
	@Transactional
	//1
	public String adicionaImagens(@PathVariable("id") Long id,@Valid NovasImagensRequest request) {
		/*
		 * 1) enviar imagens para o local onde elas vão ficar
		 * 2) pegar os links de todas as imagens		 * 
		 * 3) associar esses links com o produto em questao 
		 * 4) preciso carregar o produto
		 * 5) depois que associar eu preciso atualizar a nova versão do produto
		 */
		
		Set<String> links = uploaderFake.envia(request.getImagens());
		Produto produto = manager.find(Produto.class, id);
		produto.associaImagens(links);
		
		manager.merge(produto);
		
		return produto.toString();
		
	}


}
