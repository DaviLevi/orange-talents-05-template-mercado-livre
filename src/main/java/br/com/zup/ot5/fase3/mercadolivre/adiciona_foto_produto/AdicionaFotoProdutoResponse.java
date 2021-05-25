package br.com.zup.ot5.fase3.mercadolivre.adiciona_foto_produto;

import br.com.zup.ot5.fase3.mercadolivre.model.FotoProduto;

public class AdicionaFotoProdutoResponse {

	private String nome;
	private String url;
	
	public AdicionaFotoProdutoResponse(FotoProduto fotoProduto) {
		this.nome = fotoProduto.getNome();
		this.url = fotoProduto.getUrl();
	}

	public String getNome() {
		return nome;
	}


	public String getUrl() {
		return url;
	}
}
