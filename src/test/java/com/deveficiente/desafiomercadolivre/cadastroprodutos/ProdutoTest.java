package com.deveficiente.desafiomercadolivre.cadastroprodutos;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.deveficiente.desafiomercadolivre.cadastrocategorias.Categoria;
import com.deveficiente.desafiomercadolivre.cadastrousuario.SenhaLimpa;
import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;

public class ProdutoTest {

	@DisplayName("um produto precisa de no mínimo 3 características")
	@ParameterizedTest
	@MethodSource("geradorTeste1")
	void teste1(Collection<NovaCaracteristicaRequest> caracteristicas)
			throws Exception {
		Categoria categoria = new Categoria("categoria");
		Usuario dono = new Usuario("email@email.com.br",
				new SenhaLimpa("senhaaa"));

		new Produto("nome", 10, "descricao", BigDecimal.TEN, categoria, dono,
				caracteristicas);
	}

	static Stream<Arguments> geradorTeste1() {
		return Stream.of(
				Arguments.of(
						List.of(new NovaCaracteristicaRequest("key", "value"),
								new NovaCaracteristicaRequest("key2", "value2"),
								new NovaCaracteristicaRequest("key3",
										"value3"))),
				Arguments.of(List.of(
						new NovaCaracteristicaRequest("key", "value"),
						new NovaCaracteristicaRequest("key2", "value2"),
						new NovaCaracteristicaRequest("key3", "value3"),
						new NovaCaracteristicaRequest("key4", "value4"))));
	}

	@DisplayName("um produto nao pode ser criado com menos de 3 caracteristicas")
	@ParameterizedTest
	@MethodSource("geradorTeste2")
	void teste2(Collection<NovaCaracteristicaRequest> caracteristicas)
			throws Exception {
		Categoria categoria = new Categoria("categoria");
		Usuario dono = new Usuario("email@email.com.br",
				new SenhaLimpa("senhaaa"));

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Produto("nome", 10, "descricao", BigDecimal.TEN, categoria, dono,
					caracteristicas);			
		});
	}

	static Stream<Arguments> geradorTeste2() {
		return Stream.of(
				Arguments.of(
						List.of(new NovaCaracteristicaRequest("key", "value"),
								new NovaCaracteristicaRequest("key2",
										"value2"))),
				Arguments.of(List
						.of(new NovaCaracteristicaRequest("key", "value"))));
	}

}
