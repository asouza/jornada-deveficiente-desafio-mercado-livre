package com.deveficiente.desafiomercadolivre.cadastroprodutos;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaCaracteristicaRequest {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;

	public NovaCaracteristicaRequest(@NotBlank String nome,
			@NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return "NovaCaracteristicaRequest [nome=" + nome + ", descricao="
				+ descricao + "]";
	}

	public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
		return new CaracteristicaProduto(nome,descricao,produto);
	}
	
	

}
