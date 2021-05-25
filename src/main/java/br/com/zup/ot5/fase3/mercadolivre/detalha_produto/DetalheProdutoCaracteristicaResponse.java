package br.com.zup.ot5.fase3.mercadolivre.detalha_produto;

import br.com.zup.ot5.fase3.mercadolivre.model.Caracteristica;

public class DetalheProdutoCaracteristicaResponse {

	private String nome;
	
	private String descricao;

	public DetalheProdutoCaracteristicaResponse(Caracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descricao = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
}
