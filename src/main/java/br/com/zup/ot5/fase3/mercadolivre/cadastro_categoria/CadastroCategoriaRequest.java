package br.com.zup.ot5.fase3.mercadolivre.cadastro_categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import br.com.zup.ot5.fase3.mercadolivre.model.Categoria;
import br.com.zup.ot5.fase3.mercadolivre.validation.ExisteEntidadeSePreenchido;
import br.com.zup.ot5.fase3.mercadolivre.validation.Unico;

public class CadastroCategoriaRequest {
	
	@NotBlank
	@Unico(classeDominio = Categoria.class, nomeCampo = "nome")
	private String nome;
	
	@ExisteEntidadeSePreenchido(classeDominio = Categoria.class)
	private Long categoriaMaeId;

	public CadastroCategoriaRequest(String nome,
			Long categoriaMaeId) {
		this.nome = nome;
		this.categoriaMaeId = categoriaMaeId;
	}
	
	public Categoria toModel(EntityManager manager) {
		
		Assert.hasText(nome, "Está sendo passado um nome vazio!");
		
		if(categoriaMaeId == null) 
			return new Categoria(this.nome, null);
		
		Categoria categoriaMae = manager.find(Categoria.class, categoriaMaeId);
		
		Assert.notNull(categoriaMae, "Está sendo inserida uma associacao com uma categoria mae inexistente!");
		
		return new Categoria(nome, categoriaMae);
			
	}
}
