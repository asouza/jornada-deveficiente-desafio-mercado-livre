package com.deveficiente.desafiomercadolivre.cadastrocategorias;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NovaCategoriaRequestTest {

	@Test
	@DisplayName("deveria cadastrar categoria sem mae")
	void teste1() throws Exception {
		NovaCategoriaRequest request = new NovaCategoriaRequest();
		request.setNome("nome");

		EntityManager manager = Mockito.mock(EntityManager.class);
		request.toModel(manager);

		Mockito.verify(manager, Mockito.never())
				.find(Mockito.eq(Categoria.class), Mockito.anyLong());
	}
	
	@Test
	@DisplayName("deveria cadastrar categoria com mae")
	void teste2() throws Exception {
		NovaCategoriaRequest request = new NovaCategoriaRequest();
		request.setNome("nome");
		request.setIdCategoriaMae(1l);
		
		EntityManager manager = Mockito.mock(EntityManager.class);
		Categoria categoriaMae = new Categoria("teste");
		
		Mockito.when(manager.find(Categoria.class, 1l)).thenReturn(categoriaMae);
		request.toModel(manager);
		
		Mockito.verify(manager).find(Categoria.class, 1l);
		
		
		
	}
}
