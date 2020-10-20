package com.deveficiente.desafiomercadolivre.cadastrousuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SenhaLimpaTest {

	@DisplayName("só aceita senha com 6 ou mais caracteres")
	@ParameterizedTest
	@CsvSource({
		"123456",
		"1234567",
		"1234567890987549573489"
	})
	void teste1(String senhaLimpa) throws Exception {
		//o teste sem assert é o efeito colateral da condição
		//na precondicao. 
		new SenhaLimpa(senhaLimpa);
	}
	
	@DisplayName("não aceita senha com menos de 6 caracteres")
	@ParameterizedTest
	@CsvSource({
		"12345",
		"12"
	})
	void teste2(String senhaLimpa) throws Exception {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new SenhaLimpa(senhaLimpa);			
		});
	}
}
