package br.com.zup.ot5.fase3.mercadolivre.adiciona_opiniao_produto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.ot5.fase3.mercadolivre.model.Opiniao;
import br.com.zup.ot5.fase3.mercadolivre.model.Produto;
import br.com.zup.ot5.fase3.mercadolivre.model.Usuario;

public class AdicionaOpiniaoProdutoRequest {

	@Min(value = 1)
	@Max(value = 5)
	@NotNull
	private Integer nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max = 500)
	private String descricao;

	public AdicionaOpiniaoProdutoRequest(Integer nota, String titulo,
			String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public Opiniao paraOpiniao(Usuario usuario, Produto produtoOpinado) {
		return new Opiniao(this.titulo, this.descricao, this.nota, usuario, produtoOpinado);
	}
}
