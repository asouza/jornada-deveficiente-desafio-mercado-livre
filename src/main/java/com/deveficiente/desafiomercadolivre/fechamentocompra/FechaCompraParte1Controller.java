package com.deveficiente.desafiomercadolivre.fechamentocompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.deveficiente.desafiomercadolivre.adicionapergunta.Emails;
import com.deveficiente.desafiomercadolivre.cadastroprodutos.Produto;
import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;
import com.deveficiente.desafiomercadolivre.cadastrousuario.UsuarioRepository;

@RestController
public class FechaCompraParte1Controller {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	// 1
	private UsuarioRepository usuarioRepository;
	@Autowired
	//1
	private Emails emails;

	@PostMapping(value = "/api/compras")
	@Transactional
	//1
	public String cria(@RequestBody @Valid NovaCompraRequest request,
			UriComponentsBuilder uriComponentsBuilder) throws BindException {

		// 1
		Produto produtoASerComprado = manager.find(Produto.class,
				request.getIdProduto());

		int quantidade = request.getQuantidade();
		boolean abateu = produtoASerComprado.abataEstoque(quantidade);

		// 1
		if (abateu) {
			Usuario comprador = usuarioRepository
					.findByEmail("seuemail2@deveficiente.com").get();
			//1
			GatewayPagamento gateway = request.getGateway();

			//1
			Compra novaCompra = new Compra(produtoASerComprado, quantidade,
					comprador, gateway);
			manager.persist(novaCompra);
			emails.novaCompra(novaCompra);
			
			return novaCompra.urlRedirecionamento(uriComponentsBuilder);
									
		}

		BindException problemaComEstoque = new BindException(request,
				"novaCompraRequest");
		problemaComEstoque.reject(null,
				"Não foi possível realizar a compra por conta do estoque");

		throw problemaComEstoque;

	}

}
