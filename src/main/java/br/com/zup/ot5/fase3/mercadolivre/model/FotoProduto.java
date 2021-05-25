package br.com.zup.ot5.fase3.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
public class FotoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@URL
	@NotBlank
	private String url;
	
	@NotBlank
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ProdutoID", nullable = false)
	@NotNull
	private Produto produto;

	@Deprecated public FotoProduto() {}
	
	public FotoProduto(@URL @NotBlank String url, @NotBlank String nome, @NotNull Produto produto) {
		this.url = url;
		this.produto = produto;
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public String getNome() {
		return nome;
	}
}
