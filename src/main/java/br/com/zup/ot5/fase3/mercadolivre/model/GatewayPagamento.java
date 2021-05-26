package br.com.zup.ot5.fase3.mercadolivre.model;

import br.com.zup.ot5.fase3.mercadolivre.compra_produto_parte_1.ResolvedorTransacao;
import br.com.zup.ot5.fase3.mercadolivre.compra_produto_parte_1.ResolvedorTransacaoPagseguro;
import br.com.zup.ot5.fase3.mercadolivre.compra_produto_parte_1.ResolvedorTransacaoPaypal;

public enum GatewayPagamento {

	PAYPAL(new ResolvedorTransacaoPaypal()), PAGSEGURO(new ResolvedorTransacaoPagseguro());
	
	private GatewayPagamento(ResolvedorTransacao resolvedor) {
		this.resolvedor = resolvedor;
	}
	
	private final ResolvedorTransacao resolvedor;
	
	public String resolveDirecionamento(Long idCompra) {
		return resolvedor.resolva(idCompra);
	}
}
