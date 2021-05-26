package br.com.zup.ot5.fase3.mercadolivre.compra_produto;

import org.springframework.stereotype.Component;

import br.com.zup.ot5.fase3.mercadolivre.model.Compra;
import br.com.zup.ot5.fase3.mercadolivre.model.GatewayPagamento;

@Component
public class ProcessadorPagamento {

	public String processaPagamento(Compra compra) {
		Long idCompra = compra.getId();
		return resolveTransacaoPara(idCompra, compra.getGatewayEscolhido());
	}
	
	private String resolveTransacaoPara(Long idCompra, GatewayPagamento gatewayEscolhido) {
		return gatewayEscolhido.resolveDirecionamento(idCompra);
	}
}
