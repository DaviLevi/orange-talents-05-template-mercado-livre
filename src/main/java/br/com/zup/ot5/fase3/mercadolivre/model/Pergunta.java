package br.com.zup.ot5.fase3.mercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Pergunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PerguntaID")
	private Long id;
	
	@NotBlank
	@Column(name = "Titulo", nullable = false)
	private String titulo;
	
	@CreationTimestamp
	@Column(name = "DataCriacao")
	private LocalDateTime dataCriacao;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "UsuarioID", nullable = false)
	@NotNull
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ProdutoID", nullable = false)
	@NotNull
	private Produto produto;

	@Deprecated public Pergunta() {}
	
	public Pergunta(@NotBlank String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {
		super();
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}

	public String getTitulo() {
		return titulo;
	}
}
