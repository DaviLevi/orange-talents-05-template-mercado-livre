package br.com.zup.ot5.fase3.mercadolivre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CategoriaID")
	private Long id;

	@NotBlank
	@Column(name = "Nome", unique = true)
	private String nome;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(nullable = true, name = "CategoriaMaeID")
	private Categoria categoriaMae;

	
	@Deprecated public Categoria() {}
	
	public Categoria(Long id) {
		this.id = id;
	}
	
	public Categoria(@NotBlank String nome, Categoria categoriaMae) {
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}
}
