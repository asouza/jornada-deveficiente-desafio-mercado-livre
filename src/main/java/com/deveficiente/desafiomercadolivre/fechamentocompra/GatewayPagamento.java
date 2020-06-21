package com.deveficiente.desafiomercadolivre.fechamentocompra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

	pagseguro {
		@Override
		String criaUrlRetorno(Compra compra,
				UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPagseguro = uriComponentsBuilder
					.path("/retorno-pagseguro/{id}")
					.buildAndExpand(compra.getId()).toString();

			return "pagseguro.com/" + compra.getId() + "?redirectUrl="
					+ urlRetornoPagseguro;
		}
	},
	paypal {
		@Override
		String criaUrlRetorno(Compra compra,
				UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPaypal = uriComponentsBuilder
					.path("/retorno-paypal/{id}").buildAndExpand(compra.getId())
					.toString();

			return "paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
		}
	};

	abstract String criaUrlRetorno(Compra compra,
			UriComponentsBuilder uriComponentsBuilder);
}
