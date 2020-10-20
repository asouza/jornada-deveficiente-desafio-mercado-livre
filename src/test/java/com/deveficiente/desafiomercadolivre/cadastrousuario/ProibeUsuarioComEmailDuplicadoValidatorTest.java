package com.deveficiente.desafiomercadolivre.cadastrousuario;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class ProibeUsuarioComEmailDuplicadoValidatorTest {

	private UsuarioRepository usuarioRepository = Mockito
			.mock(UsuarioRepository.class);
	private ProibeUsuarioComEmailDuplicadoValidator validador = new ProibeUsuarioComEmailDuplicadoValidator(
			usuarioRepository);
	private Object target = new NovoUsuarioRequest("email@email.com.br", "senhaa");
	private Errors errors = new BeanPropertyBindingResult(target, "teste");
	
	@Test
	@DisplayName("deveria bloquear email duplicado")
	void teste1() throws Exception {
		Mockito.when(usuarioRepository.findByEmail("email@email.com.br"))
				.thenReturn(Optional.of(new Usuario("email@email.com.br",
						new SenhaLimpa("senhaa"))));

		validador.validate(target, errors);
		
		Assertions.assertEquals(1, errors.getErrorCount());
		Assertions.assertTrue(errors.hasFieldErrors("email"));
	}
	
	@Test
	@DisplayName("deveria liberar email novo")
	void teste2() throws Exception {
		Mockito.when(usuarioRepository.findByEmail("email@email.com.br"))
		.thenReturn(Optional.empty());
		
		validador.validate(target, errors);
		
		Assertions.assertEquals(0, errors.getErrorCount());
	}
	
}
