package br.com.zup.ot5.fase3.mercadolivre.adiciona_pergunta_produto;

import javax.validation.constraints.NotBlank;

import br.com.zup.ot5.fase3.mercadolivre.model.Pergunta;
import br.com.zup.ot5.fase3.mercadolivre.model.Produto;
import br.com.zup.ot5.fase3.mercadolivre.model.Usuario;

public class AdicionaPerguntaProdutoRequest {

	@NotBlank
	private String titulo;

	public AdicionaPerguntaProdutoRequest() {}
	
	public AdicionaPerguntaProdutoRequest(String titulo) {
		this.titulo = titulo;
	}
	
	public Pergunta paraPergunta( Usuario usuario, Produto produto) {
		return new Pergunta(this.titulo, usuario, produto);
	}
	
	public String getTitulo() {return this.titulo;}
}
