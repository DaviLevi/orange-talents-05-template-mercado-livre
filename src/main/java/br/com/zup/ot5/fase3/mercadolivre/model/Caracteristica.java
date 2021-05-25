package br.com.zup.ot5.fase3.mercadolivre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Caracteristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CaracteristicaID")
	private Long id;
	
	@Column(name = "Nome")
	private String nome;
	
	@Column(name = "Descricao")
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ProdutoID", nullable = false)
	private Produto produto;

	@Deprecated public Caracteristica() {}
	
	public Caracteristica(String nome, String descricao, Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
