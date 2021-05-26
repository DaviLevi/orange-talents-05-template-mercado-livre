package br.com.zup.ot5.fase3.mercadolivre.compra_produto;

public class ResolvedorTransacaoPagseguro implements ResolvedorTransacao{

	private static final String URL_AFTER_PAYMENT = "http://mercadolivre.com.br/home?depoisPagamento=true&gateway=pagseguro";
	
	@Override
	public String resolva(Long idCompra) {
		return String.format("pagseguro.com?buyerId={%s}&redirectUrl={%s}", idCompra, URL_AFTER_PAYMENT);
	}

}
