package com.deveficiente.desafiomercadolivre.fechamentocompra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {

	@Positive
	private int quantidade;
	@NotNull
	private Long idProduto;
	@NotNull
	private GatewayPagamento gateway;
	
	public NovaCompraRequest(@Positive int quantidade,
			@NotNull Long idProduto,GatewayPagamento gateway) {
		super();
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gateway = gateway;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}
	
	
}
