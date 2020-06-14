package com.deveficiente.desafiomercadolivre.cadastroprodutos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.deveficiente.desafiomercadolivre.cadastrocategorias.Categoria;
import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;
import com.deveficiente.desafiomercadolivre.compartilhado.ExistsId;
import com.deveficiente.desafiomercadolivre.compartilhado.UniqueValue;

public class NovoProdutoRequest {

	@NotBlank
	@UniqueValue(domainClass = Produto.class,fieldName = "nome")
	private String nome;
	@Positive
	@NotNull
	private Integer quantidade;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;
	@Size(min = 3)
	@Valid
	// 1
	private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();

	public NovoProdutoRequest(@NotBlank String nome, @Positive int quantidade,
			@NotBlank @Length(max = 1000) String descricao,
			@NotNull @Positive BigDecimal valor, @NotNull Long idCategoria,
			List<NovaCaracteristicaRequest> caracteristicas) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);		
	}

	public List<NovaCaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(
			List<NovaCaracteristicaRequest> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	@Override
	public String toString() {
		return "NovoProdutoRequest [nome=" + nome + ", quantidade=" + quantidade
				+ ", descricao=" + descricao + ", valor=" + valor
				+ ", idCategoria=" + idCategoria + ", caracteristicas="
				+ caracteristicas + "]";
	}

	// 1
	public Produto toModel(EntityManager manager, Usuario dono) {
		// 1
		Categoria categoria = manager.find(Categoria.class, idCategoria);

		// 1
		return new Produto(nome, quantidade, descricao, valor, categoria, dono,
				caracteristicas);
	}

	public Set<String> buscaCaracteristicasIguais() {
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		// 1
		for (NovaCaracteristicaRequest caracteristica : caracteristicas) {
			String nome = caracteristica.getNome();
			// 1
			if (!nomesIguais.add(nome)) {
				resultados.add(nome);
			}
		}
		return resultados;
	}

}
