package br.com.zup.ot5.fase3.mercadolivre.compra_produto_parte_2;

import javax.validation.constraints.NotNull;

import br.com.zup.ot5.fase3.mercadolivre.model.Compra;
import br.com.zup.ot5.fase3.mercadolivre.model.StatusTransacao;
import br.com.zup.ot5.fase3.mercadolivre.model.Transacao;
import io.jsonwebtoken.lang.Assert;

public class RetornoGatewayPagseguroRequest implements RetornoGatewayPagamentoRequest{

	@NotNull
	private String idPagamento;
	
	@NotNull
	private StatusPagamentoPagseguro status;

	public RetornoGatewayPagseguroRequest(@NotNull String idPagamento, @NotNull StatusPagamentoPagseguro status) {
		this.idPagamento = idPagamento;
		this.status = status;
	}

	private StatusTransacao getStatusTransacao() {
		if(status == StatusPagamentoPagseguro.ERRO)
			return StatusTransacao.FALHA;
		return StatusTransacao.SUCESSO;
	}

	@Override
	public Transacao paraTransacao(Compra compra) {
		
		Assert.state(compra != null, "Está sendo instanciada uma transacao sem compra!");
		
		return new Transacao(this.idPagamento, this.getStatusTransacao(), compra);
	}

	
}
