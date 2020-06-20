package com.deveficiente.desafiomercadolivre.adicionapergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.deveficiente.desafiomercadolivre.cadastroprodutos.Produto;
import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;

public class NovaPerguntaRequest {

	@NotBlank
	private String titulo;
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "NovaPerguntaRequest [titulo=" + titulo + "]";
	}

	public Pergunta toModel(@NotNull @Valid Usuario interessada,
			@NotNull @Valid Produto produto) {
		return new Pergunta(titulo, interessada, produto);
	}

}
