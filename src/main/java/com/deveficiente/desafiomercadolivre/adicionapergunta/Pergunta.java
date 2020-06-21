package com.deveficiente.desafiomercadolivre.adicionapergunta;

import java.time.LocalDate;

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
public class Pergunta implements Comparable<Pergunta>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String titulo;
	@ManyToOne
	private @NotNull @Valid Usuario interessada;
	@ManyToOne
	private @NotNull @Valid Produto produto;
	private LocalDate instante;
	
	@Deprecated
	public Pergunta() {

	}

	public Pergunta(@NotBlank String titulo,
			@NotNull @Valid Usuario interessada,
			@NotNull @Valid Produto produto) {
				this.titulo = titulo;
				this.interessada = interessada;
				this.produto = produto;
				this.instante = LocalDate.now();
	}

	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", interessada="
				+ interessada + ", produto=" + produto + "]";
	}

	public Usuario getInteressada() {
		return interessada;
	}

	public Usuario getDonoProduto() {
		return produto.getDono();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((interessada == null) ? 0 : interessada.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Pergunta other = (Pergunta) obj;
		if (interessada == null) {
			if (other.interessada != null)
				return false;
		} else if (!interessada.equals(other.interessada))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public int compareTo(Pergunta o) {
		return this.titulo.compareTo(o.titulo);
	}

	public String getTitulo() {
		return titulo;
	}
	
	
	
	
	

}
