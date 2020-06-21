package com.deveficiente.desafiomercadolivre.fechamentocompra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPaypalRequest implements RetornoGatewayPagamento {

	@Min(0)
	@Max(1)
	private int status;
	@NotBlank
	private String idTransacao;

	public RetornoPaypalRequest(@Min(0) @Max(1) int status,
			@NotBlank String idTransacao) {
		super();
		this.status = status;
		this.idTransacao = idTransacao;
	}

	public Transacao toTransacao(Compra compra) {
		StatusTransacao statusCalculado = this.status == 0 ? StatusTransacao.erro
				: StatusTransacao.sucesso;
		
		return new Transacao(statusCalculado, idTransacao, compra);
	}

}
