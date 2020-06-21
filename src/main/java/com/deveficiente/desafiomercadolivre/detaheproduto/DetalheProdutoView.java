package com.deveficiente.desafiomercadolivre.detaheproduto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.SortedSet;

import com.deveficiente.desafiomercadolivre.cadastroprodutos.Produto;

public class DetalheProdutoView {

	private String descricao;
	private String nome;
	private BigDecimal preco;
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private Set<String> linksImagens;
	private SortedSet<String> perguntas;

	public DetalheProdutoView(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.caracteristicas = produto
				.mapeiaCaracteristicas(DetalheProdutoCaracteristica::new);
		this.linksImagens = produto.mapeiaImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());

	}
	
	public SortedSet<String> getPerguntas() {
		return perguntas;
	}
	
	public Set<String> getLinksImagens() {
		return linksImagens;
	}
	
	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

}
