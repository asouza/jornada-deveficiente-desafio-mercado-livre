package com.deveficiente.desafiomercadolivre.cadastroprodutos;

import java.math.BigDecimal;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.deveficiente.desafiomercadolivre.cadastrocategorias.Categoria;
import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;

public class Produto {

	private @NotBlank String nome;
	private @Positive int quantidade;
	private @NotBlank @Length(max = 1000) String descricao;
	private @NotNull @Positive BigDecimal valor;
	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;
	@NotNull @Valid
	@ManyToOne
	private Usuario dono;

	public Produto(@NotBlank String nome, @Positive int quantidade,
			@NotBlank @Length(max = 1000) String descricao,
			@NotNull @Positive BigDecimal valor,
			@NotNull @Valid Categoria categoria, @NotNull @Valid Usuario dono) {
		
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
		this.dono = dono;
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", quantidade=" + quantidade
				+ ", descricao=" + descricao + ", valor=" + valor
				+ ", categoria=" + categoria + ", dono=" + dono + "]";
	}
	
	

}
