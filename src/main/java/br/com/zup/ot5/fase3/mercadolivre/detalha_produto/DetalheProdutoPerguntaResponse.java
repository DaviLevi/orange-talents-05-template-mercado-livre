package br.com.zup.ot5.fase3.mercadolivre.detalha_produto;

import br.com.zup.ot5.fase3.mercadolivre.model.Pergunta;

public class DetalheProdutoPerguntaResponse {

	private String titulo;

	public DetalheProdutoPerguntaResponse(Pergunta pergunta) {
		this.titulo = pergunta.getTitulo();
	}

	public String getTitulo() {
		return titulo;
	}
	
}
