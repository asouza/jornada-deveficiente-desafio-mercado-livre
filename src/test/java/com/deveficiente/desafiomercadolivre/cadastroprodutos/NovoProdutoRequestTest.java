package com.deveficiente.desafiomercadolivre.cadastroprodutos;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class NovoProdutoRequestTest {

	@ParameterizedTest
	@MethodSource("gerador")
	@DisplayName("cria produto com diversos tipos de caracteristicas")
	void teste1(int esperado,List<NovaCaracteristicaRequest> novasCaracteristicas) throws Exception {
		NovoProdutoRequest request = new NovoProdutoRequest("nome", 10, "descricao", BigDecimal.TEN, 1l,
				novasCaracteristicas);
		
		Assertions.assertEquals(esperado,request.buscaCaracteristicasIguais().size());
	}
	
	private static Stream<Arguments> gerador(){
		return Stream.of(
				Arguments.of(0,List.of()),
				Arguments.of(0,List.of(new NovaCaracteristicaRequest("key", "value"))),
				Arguments.of(0,List.of(new NovaCaracteristicaRequest("key", "value"),new NovaCaracteristicaRequest("key1", "value1"))),
				Arguments.of(1,List.of(
						new NovaCaracteristicaRequest("key", "value"),
						new NovaCaracteristicaRequest("key", "value")))
			);
	}
}
