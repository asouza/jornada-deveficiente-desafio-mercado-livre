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

	@Override
	public String toString() {
		return "Opiniao [id=" + id + ", nota=" + nota + ", titulo=" + titulo
				+ ", descricao=" + descricao + ", produto=" + produto
				+ ", consumidor=" + consumidor + "]";
	}
	
	

}
