package com.deveficiente.desafiomercadolivre.fechamentocompra;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindException;
import org.springframework.web.util.UriComponentsBuilder;

import com.deveficiente.desafiomercadolivre.adicionapergunta.Emails;
import com.deveficiente.desafiomercadolivre.cadastrocategorias.Categoria;
import com.deveficiente.desafiomercadolivre.cadastroprodutos.NovaCaracteristicaRequest;
import com.deveficiente.desafiomercadolivre.cadastroprodutos.Produto;
import com.deveficiente.desafiomercadolivre.cadastrousuario.SenhaLimpa;
import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;
import com.deveficiente.desafiomercadolivre.cadastrousuario.UsuarioRepository;

public class FechaCompraParte1ControllerTest {

	private EntityManager manager = Mockito.mock(EntityManager.class);
	private UsuarioRepository usuarioRepository = Mockito
			.mock(UsuarioRepository.class);
	private Emails emails = Mockito.mock(Emails.class);
	private FechaCompraParte1Controller controller = new FechaCompraParte1Controller(
			manager, usuarioRepository, emails);

	private Categoria categoria = new Categoria("nome");
	private Usuario dono = new Usuario("seuemail2@deveficiente.com",
			new SenhaLimpa("123456"));
	private Collection<NovaCaracteristicaRequest> caracteristicas = List.of(
			new NovaCaracteristicaRequest("chave", "valor"),
			new NovaCaracteristicaRequest("chave1", "valor"),
			new NovaCaracteristicaRequest("chave2", "valor"));

	private UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
			.fromHttpUrl("http://localhost:8080");
	{
		Mockito.when(
				usuarioRepository.findByEmail("seuemail2@deveficiente.com"))
				.thenReturn(Optional.of(dono));
	}

	@Test
	@DisplayName("redireciona para gateway em caso de estoque aceito")
	void teste1() throws Exception {

		Produto produto = new Produto("nome", 1, "descricao", BigDecimal.TEN,
				categoria, dono, caracteristicas);
		Mockito.when(manager.find(Produto.class, 1l)).thenReturn(produto);

		Mockito.doAnswer(invocation -> {
			Compra compraSendoSalva = invocation.<Compra>getArgument(0);
			ReflectionTestUtils.setField(compraSendoSalva, "id", 1l);
			return null;
		}).when(manager).persist(Mockito.any(Compra.class));

		NovaCompraRequest request = new NovaCompraRequest(1, 1l,
				GatewayPagamento.pagseguro);
		String endereco = controller.cria(request, uriComponentsBuilder);

		Assertions.assertEquals(
				"pagseguro.com/1?redirectUrl=http://localhost:8080/retorno-pagseguro/1",
				endereco);

	}
	
	@Test
	@DisplayName("solta exception em caso de estoque nao disponível")
	void teste2() throws Exception {
		
		Produto produto = new Produto("nome", 1, "descricao", BigDecimal.TEN,
				categoria, dono, caracteristicas);
		Mockito.when(manager.find(Produto.class, 1l)).thenReturn(produto);
		
		Mockito.doAnswer(invocation -> {
			Compra compraSendoSalva = invocation.<Compra>getArgument(0);
			ReflectionTestUtils.setField(compraSendoSalva, "id", 1l);
			return null;
		}).when(manager).persist(Mockito.any(Compra.class));
		
		NovaCompraRequest request = new NovaCompraRequest(2, 1l,
				GatewayPagamento.pagseguro);
		
		Assertions.assertThrows(BindException.class, () -> {
			controller.cria(request, uriComponentsBuilder);			
		});
		
		
	}
}
