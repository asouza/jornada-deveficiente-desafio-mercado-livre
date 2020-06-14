package com.deveficiente.desafiomercadolivre.cadastroprodutos;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.deveficiente.desafiomercadolivre.cadastrocategorias.Categoria;
import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;
import com.deveficiente.desafiomercadolivre.compartilhado.ExistsId;

public class NovoProdutoRequest {

	@NotBlank
	private String nome;
	@Positive
	private int quantidade;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	public NovoProdutoRequest(@NotBlank String nome, @Positive int quantidade,
			@NotBlank @Length(max = 1000) String descricao,
			@NotNull @Positive BigDecimal valor, @NotNull Long idCategoria) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.idCategoria = idCategoria;
	}

	@Override
	public String toString() {
		return "NovoProdutoRequest [nome=" + nome + ", quantidade=" + quantidade
				+ ", descricao=" + descricao + ", valor=" + valor
				+ ", idCategoria=" + idCategoria + "]";
	}

	public Produto toModel(EntityManager manager, Usuario dono) {
		Categoria categoria = manager.find(Categoria.class,idCategoria);
		return new Produto(nome,quantidade,descricao,valor,categoria,dono);
	}

	
	
}
