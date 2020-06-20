package com.deveficiente.desafiomercadolivre.cadastroprodutos;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import com.deveficiente.desafiomercadolivre.cadastrocategorias.Categoria;
import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	private @Positive int quantidade;
	private @NotBlank @Length(max = 1000) String descricao;
	private @NotNull @Positive BigDecimal valor;
	@NotNull
	@Valid
	@ManyToOne
	// 1
	private Categoria categoria;
	@NotNull
	@Valid
	@ManyToOne
	// 1
	private Usuario dono;
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	// 1
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	//1
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	
	@Deprecated
	public Produto() {

	}

	public Produto(@NotBlank String nome, @Positive int quantidade,
			@NotBlank @Length(max = 1000) String descricao,
			@NotNull @Positive BigDecimal valor,
			@NotNull @Valid Categoria categoria, @NotNull @Valid Usuario dono,
			@Size(min = 3) @Valid Collection<NovaCaracteristicaRequest> caracteristicas) {

		this.nome = nome;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
		this.dono = dono;
		// 1
		// 1
		this.caracteristicas.addAll(caracteristicas.stream()
				.map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));

		// 1
		Assert.isTrue(this.caracteristicas.size() >= 3,
				"Todo produto precisa ter no mínimo 3 ou mais características");

	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", quantidade="
				+ quantidade + ", descricao=" + descricao + ", valor=" + valor
				+ ", categoria=" + categoria + ", dono=" + dono
				+ ", caracteristicas=" + caracteristicas + ", imagens="
				+ imagens + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream()
				.map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		
		this.imagens.addAll(imagens);
	}

}
