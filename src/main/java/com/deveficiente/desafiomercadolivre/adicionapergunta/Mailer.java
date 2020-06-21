package com.deveficiente.desafiomercadolivre.adicionapergunta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface Mailer {

	/**
	 * 
	 * @param body     corpo do email
	 * @param subject  assunto do email
	 * @param nameFrom nome para aparecer no provedor de email
	 * @param from     email de origem
	 * @param to       email de destino
	 */
	void send(@NotBlank String body, @NotBlank String subject,
			@NotBlank String nameFrom, @NotBlank @Email String from, @NotBlank @Email String to);

}
