package com.deveficiente.desafiomercadolivre.outrossistemas;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutrosSistemasController {

	@PostMapping(value = "/notas-fiscais")
	public void criaNota(Long idCompra,Long idComprador) throws InterruptedException {
		System.out.println("criando nota para "+idCompra+" do comprador "+idComprador);
		Thread.sleep(150);
	}
	
	@PostMapping(value = "/ranking")
	public void ranking(Long idCompra,Long idVendedor) throws InterruptedException {
		System.out.println("criando nota para "+idCompra+" do comprador "+idVendedor);
		Thread.sleep(150);
	}

}
