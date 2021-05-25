package br.com.zup.ot5.fase3.mercadolivre.detalha_produto;

import br.com.zup.ot5.fase3.mercadolivre.model.Opiniao;

public class DetalheProdutoOpiniaoResponse {

	private String titulo;
	
	private String descricao;
	
	private Integer nota;

	public DetalheProdutoOpiniaoResponse(Opiniao opiniao) {
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
		this.nota = opiniao.getNota();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getNota() {
		return nota;
	}
	
	
}
