package com.deveficiente.desafiomercadolivre.adicionaopiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.deveficiente.desafiomercadolivre.cadastroprodutos.Produto;
import com.deveficiente.desafiomercadolivre.cadastrousuario.Usuario;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @Min(1) @Max(5) int nota;
	private @NotBlank String titulo;
	private @NotBlank @Size(max = 500) String descricao;
	@ManyToOne
	private @NotNull @Valid Produto produto;
	@ManyToOne
	private @NotNull @Valid Usuario consumidor;
	
	@Deprecated
	public Opiniao() {

	}

	public Opiniao(@Min(1) @Max(5) int nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao,
			@NotNull @Valid Produto produto,
			@NotNull @Valid Usuario consumidor) {
				this.nota = nota;
				this.titulo = titulo;
				this.descricao = descricao;
				this.produto = produto;
				this.consumidor = consumidor;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "Opiniao [id=" + id + ", nota=" + nota + ", titulo=" + titulo
				+ ", descricao=" + descricao + ", produto=" + produto
				+ ", consumidor=" + consumidor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((consumidor == null) ? 0 : consumidor.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		Opiniao other = (Opiniao) obj;
		if (consumidor == null) {
			if (other.consumidor != null)
				return false;
		} else if (!consumidor.equals(other.consumidor))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
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

	public int getNota() {
		return nota;
	}
	
	
	
	

}
