package com.deveficiente.desafiomercadolivre.adicionapergunta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.deveficiente.desafiomercadolivre.cadastroprodutos.Produto;
import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String titulo;
	@ManyToOne
	private @NotNull @Valid Usuario interessada;
	@ManyToOne
	private @NotNull @Valid Produto produto;

	public Pergunta(@NotBlank String titulo,
			@NotNull @Valid Usuario interessada,
			@NotNull @Valid Produto produto) {
				this.titulo = titulo;
				this.interessada = interessada;
				this.produto = produto;
	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", interessada="
				+ interessada + ", produto=" + produto + "]";
	}
	
	

}
