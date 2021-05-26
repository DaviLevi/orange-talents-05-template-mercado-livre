package br.com.zup.ot5.fase3.mercadolivre.compra_produto;

public class ResolvedorTransacaoPaypal implements ResolvedorTransacao{

	private static final String URL_AFTER_PAYMENT = "http://mercadolivre.com.br/home?depoisPagamento=true&gateway=paypal";
	
	@Override
	public String resolva(Long idCompra) {
		return String.format("paypal.com?buyerId={%s}&redirectUrl={%s}", idCompra, URL_AFTER_PAYMENT);
	}

}
