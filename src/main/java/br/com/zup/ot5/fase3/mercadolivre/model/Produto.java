package br.com.zup.ot5.fase3.mercadolivre.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zup.ot5.fase3.mercadolivre.cadastro_produto.CadastroCaracteristicaRequest;
import io.jsonwebtoken.lang.Assert;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProdutoID")
	private Long id;
	
	@Column(name = "Nome")
	@NotBlank
	private String nome;
	
	@Column(name = "Descricao")
	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@Column(name = "Valor")
	@Positive
	@NotNull
	private BigDecimal valor;

	@Column(name = "QuantidadeDisponivel")
	@PositiveOrZero
	@NotNull
	private Integer quantidadeDisponivel;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "produto", cascade = CascadeType.ALL)
	@Size(min = 3)
	@NotNull
	@Valid
	private Set<Caracteristica> caracteristicas = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CategoriaID", nullable = false)
	private Categoria categoria;
	
	@CreationTimestamp
	private LocalDateTime dataCadastro;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
	private Set<FotoProduto> fotosProduto = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "UsuarioID", nullable = false)
	@NotNull
	private Usuario usuario;

	@OneToMany(mappedBy = "produtoOpinado")
	private Set<Opiniao> opinioes = new HashSet<>();
	
	@OneToMany(mappedBy = "produto")
	private Set<Pergunta> perguntas = new HashSet<>();
	
	@Deprecated public Produto() {}
	
	public Produto(@NotBlank String nome, @NotBlank @Size(max = 1000) String descricao,
			@Positive @NotNull BigDecimal valor, @PositiveOrZero @NotNull Integer quantidadeDisponivel,
			@Size(min = 3) @NotNull @Valid List<CadastroCaracteristicaRequest> caracteristicas, Categoria categoria, @NotNull Usuario usuarioLogado) {
		
		Assert.state(nome != null, "O nome do produto nao pode ser nulo!");
		Assert.state(usuarioLogado != null, "O usuario logado nao pode ser nulo!");
		
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.caracteristicas.addAll(caracteristicas.stream().map(c -> new Caracteristica(c.getNome(), c.getDescricao(), this)).collect(Collectors.toSet()));
		this.categoria = categoria;
		this.usuario = usuarioLogado;
		
		Assert.state(this.caracteristicas.size() >= 3, "Estamos tentando persistir um produto com menos de 3 caracteristicas!!");
	}

	public void adicionaFotosProduto(Set<FotoProduto> fotosProduto) {
		
		Assert.state(fotosProduto.size() > 0, "Está sendo adicionada uma lista de foto vazia");
		
		this.fotosProduto = fotosProduto;
	}
	
	public boolean naoPertenceAoUsuario(Usuario usuario) {
		return this.usuario.getId() != usuario.getId();
	}
	
	public String getEmailVendedor() {
		return this.usuario.getUsername();
	}
	
	public Double calculaMediaNotas(){
		return this.opinioes.stream().mapToInt(Opiniao::getNota).average().orElse(0);
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public BigDecimal getPreco() {
		return this.valor;
	}
	
	public String getNome() {
		return this.nome;
	}

	public Map<Integer, Long> calculaQuantidadeOpinioesPorNota() {
		return this.opinioes.stream().collect(Collectors.groupingBy(Opiniao::getNota, Collectors.counting()));
	}
	
	public Long calculaQuantidadeOpinioes() {
		return this.opinioes.stream().collect(Collectors.counting());
	}
	
	public Set<FotoProduto> getFotosProduto(){
		return this.fotosProduto;
	} 
	
	public Set<Caracteristica> getCaracteristicas(){
		return this.caracteristicas;
	}
	
	public Set<Pergunta> getPerguntas(){
		return this.perguntas;
	}
	
	public Set<Opiniao> getOpinioes(){
		return this.opinioes;
	}
	
	public boolean possuiEstoquePara(Integer quantidadeRequisitada) {
		return this.quantidadeDisponivel >= quantidadeRequisitada;
	}
	
	public void abateEstoque(Integer quantidadeCompra) {
		Assert.state(this.quantidadeDisponivel >= quantidadeCompra, "Está sendo comprada uma quantidade maior que o disponivel do produto!");
		this.quantidadeDisponivel -= quantidadeCompra;
	}
}
