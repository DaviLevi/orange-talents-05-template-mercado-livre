package br.com.zup.ot5.fase3.mercadolivre.compra_produto_parte_1;

public class ResolvedorTransacaoPagseguro implements ResolvedorTransacao{

	private static final String URL_AFTER_PAYMENT = "/retorno-pagseguro/%d";
	
	@Override
	public String resolva(Long idCompra) {
		return String.format("pagseguro.com?buyerId=%s&redirectUrl=%s", idCompra, String.format(URL_AFTER_PAYMENT, idCompra));
	}

}
