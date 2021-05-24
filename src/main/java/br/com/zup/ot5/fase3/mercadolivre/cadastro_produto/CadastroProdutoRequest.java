package br.com.zup.ot5.fase3.mercadolivre.cadastro_produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import br.com.zup.ot5.fase3.mercadolivre.model.Categoria;
import br.com.zup.ot5.fase3.mercadolivre.model.Produto;
import br.com.zup.ot5.fase3.mercadolivre.model.Usuario;
import br.com.zup.ot5.fase3.mercadolivre.validation.ExisteId;

public class CadastroProdutoRequest {

	@NotBlank
	private String nome;
	
	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@Positive
	@NotNull
	private BigDecimal valor;

	@PositiveOrZero
	@NotNull
	private Integer quantidadeDisponivel;
	
	@Size(min = 3)
	@NotNull
	@Valid
	private List<CadastroCaracteristicaRequest> caracteristicas = new ArrayList<>();
	
	@NotNull
	@ExisteId(classeDominio = Categoria.class)
	private Long categoriaId;

	public CadastroProdutoRequest(String nome, String descricao,
			BigDecimal valor, Integer quantidadeDisponivel,
			List<CadastroCaracteristicaRequest> caracteristicas,
			Long categoriaId) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.caracteristicas = caracteristicas;
		this.categoriaId = categoriaId;
	}
	
	public Produto paraDominio(EntityManager manager, Usuario usuarioLogado) {
		
		Categoria categoriaBuscada = manager.find(Categoria.class, this.categoriaId);
		
		Assert.notNull(categoriaBuscada, "Está sendo associada uma categoria inexistente ao produto em criação!");
		
		return new Produto(this.nome, this.descricao, this.valor, this.quantidadeDisponivel, caracteristicas, categoriaBuscada, usuarioLogado);
	}

	public List<CadastroCaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}
	
	public boolean temCaracteristicasComNomesIguais() {
		Set<CadastroCaracteristicaRequest> caracteristicas = new HashSet<>();
		
		for(CadastroCaracteristicaRequest caracteristica : this.caracteristicas) {
			if(caracteristicas.add(caracteristica))
				continue;
			else
				return true;
		}
		
		return false;
	}
	
}
