package com.deveficiente.desafiomercadolivre.cadastroprodutos;

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
	private UsuarioRepository usuarioRepository;
	
	@InitBinder(value = "novoProdutoRequest")
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
	}

	@PostMapping(value = "/produtos")
	@Transactional
	public String cria(@RequestBody @Valid NovoProdutoRequest request) {
		//simulando o usuario logado
		Usuario dono = usuarioRepository.findByEmail("alberto@deveficiente.com").get();		
		Produto produto = request.toModel(manager,dono);
		
		manager.persist(produto);
		return produto.toString();
	}
	
	@PostMapping(value = "/produtos/{id}/fotos")
	public String postMethodName(@PathVariable("id") Long idProduto ,@Valid ImagensProdutoRequest request) {
		
		Produto produto = manager.find(Produto.class, idProduto);
		
		FakeUploader uploader = new FakeUploader();
		/*
		 * salvar os arquivos em algum lugar de fora
		 * isso vai me retornar os arquivos, mas eles ainda precisam ser associados(LinkArquivo)
		 * adiciona tais links como imagens do produto 
		 * faz o merge no produto
		 */
		Set<String> links = uploader.upload(request.getImagens());
		System.out.println(links);
		//produto.associaImagens(links);
		
		//manager.merge(produto);
		
		return produto.toString();
	}


}
