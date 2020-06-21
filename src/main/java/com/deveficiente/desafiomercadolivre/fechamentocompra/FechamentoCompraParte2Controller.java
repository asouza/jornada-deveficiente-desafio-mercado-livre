package com.deveficiente.desafiomercadolivre.fechamentocompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FechamentoCompraParte2Controller {
	
	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/retorno-pagseguro/{id}")
	@Transactional
	public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagseguroRequest request) {
			
		Compra compra = manager.find(Compra.class, idCompra);
		compra.adicionaTransacao(request);
		
		manager.merge(compra);
		
		return compra.toString();
	}

}
