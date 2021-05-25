package br.com.zup.ot5.fase3.mercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max = 500)
	private String descricao;
	
	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private Integer nota;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioID", nullable = false)
	@NotNull
	private Usuario usuario;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "ProdutoID", nullable = false)
	@NotNull
	private Produto produtoOpinado;

	
	@Deprecated public Opiniao() {}
	
	public Opiniao(@NotBlank String titulo, @NotBlank @Size(max = 500) String descricao,
			@NotNull @Min(1) @Max(5) Integer nota, @NotNull Usuario usuario, @NotNull Produto produtoOpinado) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.nota = nota;
		this.usuario = usuario;
		this.produtoOpinado = produtoOpinado;
	}
	
	public Integer getNota() {
		return this.nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
}
