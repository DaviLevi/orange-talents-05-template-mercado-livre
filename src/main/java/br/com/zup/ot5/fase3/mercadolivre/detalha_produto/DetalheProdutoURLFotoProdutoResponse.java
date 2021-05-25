package br.com.zup.ot5.fase3.mercadolivre.detalha_produto;

import br.com.zup.ot5.fase3.mercadolivre.model.FotoProduto;

public class DetalheProdutoURLFotoProdutoResponse {

	private String url;
	
	private String nome;

	public DetalheProdutoURLFotoProdutoResponse (FotoProduto fotoProduto) {
		this.nome = fotoProduto.getNome();
		this.url = fotoProduto.getUrl();
	}
	
	public String getUrl() {
		return url;
	}

	public String getNome() {
		return nome;
	}
}
