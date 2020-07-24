package com.deveficiente.desafiomercadolivre.fechamentocompra;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.deveficiente.desafiomercadolivre.cadastrocategorias.Categoria;
import com.deveficiente.desafiomercadolivre.cadastroprodutos.NovaCaracteristicaRequest;
import com.deveficiente.desafiomercadolivre.cadastroprodutos.Produto;
import com.deveficiente.desafiomercadolivre.cadastrousuario.SenhaLimpa;
import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;

public class CompraTest {

	/*
	 * 1) aceitar uma transacao com sucesso
	 * 2) não pode aceitar mais de uma transacao com sucesso	 * 
	 * 3) não pode aceitar transacoes com erro quando ja foi concluido com sucesso
	 * 4) pode aceitar uma transacao com erro
	 * 5) pode aceitar mais de uma transacao com erro
	 * 6) pode aceitar uma transacao com erro e uma transacao com sucesso
	 * 7) pode aceitar mais de uma transacao com erro e uma transacao com sucesso   
	 */
	
	@Test
	@DisplayName("aceitar uma transacao com sucesso")
	void deveriaAdicionarUmaTransacaoComSucesso() {
		Compra novaCompra = novaCompra();
		RetornoGatewayPagamento retornoGatewayPagamento = (compra) -> {
			return new Transacao(StatusTransacao.sucesso, "1", compra);
		};
		
		novaCompra.adicionaTransacao(retornoGatewayPagamento);
	}
	
	@Test
	@DisplayName("não pode aceitar mais de uma transacao com sucesso")
	void naoDeveriaAdicionarMaisDeUmaTransacaoComSucesso() {
		Compra novaCompra = novaCompra();
		RetornoGatewayPagamento retornoGatewayPagamento = (compra) -> {
			return new Transacao(StatusTransacao.sucesso, "1", compra);
		};
		
		novaCompra.adicionaTransacao(retornoGatewayPagamento);
		
		RetornoGatewayPagamento retornoGatewayPagamento2 = (compra) -> {
			return new Transacao(StatusTransacao.sucesso, "2", compra);
		};
		
		Assertions.assertThrows(IllegalStateException.class, () -> {
			novaCompra.adicionaTransacao(retornoGatewayPagamento2);
		});
	}
	
	@Test
	@DisplayName("não pode aceitar transacao com erro quando ja foi concluido com sucesso")
	void naoPodeAceitarTransacaoComErroQuandoJaFoiConcluidaComSucesso() {
		Compra novaCompra = novaCompra();
		RetornoGatewayPagamento retornoGatewayPagamento = (compra) -> {
			return new Transacao(StatusTransacao.sucesso, "1", compra);
		};
		
		novaCompra.adicionaTransacao(retornoGatewayPagamento);
		
		RetornoGatewayPagamento retornoGatewayPagamento2 = (compra) -> {
			return new Transacao(StatusTransacao.erro, "2", compra);
		};
		
		Assertions.assertThrows(IllegalStateException.class, () -> {
			novaCompra.adicionaTransacao(retornoGatewayPagamento2);
		});
	}
	
	private Compra novaCompra() {
		Categoria categoria = new Categoria("teste");
		Usuario dono = new Usuario("email@email.com", new SenhaLimpa("senhaa"));
		Collection<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();
		caracteristicas.add(new NovaCaracteristicaRequest("nome", "descricao"));
		caracteristicas	
				.add(new NovaCaracteristicaRequest("nome1", "descricao"));
		caracteristicas
				.add(new NovaCaracteristicaRequest("nome2", "descricao"));

		Produto produtoASerComprado = new Produto("teste", 100, "descricao",
				BigDecimal.TEN, categoria, dono, caracteristicas);

		Usuario comprador = new Usuario("comprador@email.com",
				new SenhaLimpa("senhaa"));

		GatewayPagamento gatewayPagamento = GatewayPagamento.pagseguro;

		return new Compra(produtoASerComprado, 50, comprador, gatewayPagamento);
	}
}
