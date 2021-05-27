package br.com.zup.ot5.fase3.mercadolivre.compra_produto_parte_2;

import br.com.zup.ot5.fase3.mercadolivre.model.Compra;
import br.com.zup.ot5.fase3.mercadolivre.model.Transacao;

public interface RetornoGatewayPagamentoRequest {
	
	Transacao paraTransacao(Compra compra);
}
