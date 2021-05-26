package br.com.zup.ot5.fase3.mercadolivre.compra_produto_parte_1;

public class ResolvedorTransacaoPaypal implements ResolvedorTransacao{

	private static final String URL_AFTER_PAYMENT = "/retorno-paypal/%d";
	
	@Override
	public String resolva(Long idCompra) {
		return String.format("paypal.com?buyerId=%s&redirectUrl=%s", idCompra, String.format(URL_AFTER_PAYMENT, idCompra));
	}

}
