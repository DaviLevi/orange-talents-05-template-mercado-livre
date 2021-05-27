package br.com.zup.ot5.fase3.mercadolivre.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import io.jsonwebtoken.lang.Assert;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CompraID")
	private Long id;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ProdutoID")
	private Produto produto;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CompradorID")
	private Usuario comprador;
	
	@Positive
	@NotNull
	private Integer quantidade;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private GatewayPagamento gatewayEscolhido;
	
	@NotNull
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private StatusCompra status;
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "compra")
	private Set<Transacao> transacoes = new HashSet<>();
	
	@Deprecated public Compra() {}

	public Compra(@NotNull Produto produto, @NotNull Usuario comprador, @Positive @NotNull Integer quantidade,
			@NotNull GatewayPagamento gatewayEscolhido, @NotNull BigDecimal valor) {
		this.produto = produto;
		this.comprador = comprador;
		this.quantidade = quantidade;
		this.gatewayEscolhido = gatewayEscolhido;
		this.valor = valor;
		this.status = StatusCompra.INICIADA;
	}

	public Long getId() {
		return id;
	}

	public GatewayPagamento getGatewayEscolhido() {
		return gatewayEscolhido;
	}

	public void adicionaTransacao(Transacao transacao){
		
		Assert.isTrue(! this.transacoes.contains(transacao), "Estamos tentando adicionar a mesma transacao duas vezes!");
		
		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao :: concluidaComSucesso)
									.collect(Collectors.toSet());
		
		Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(), "Nao é possivel adicionar mais de uma transacao concluida com sucesso");
	
		this.transacoes.add(transacao);
	}
	
	public String getEmailComprador() {
		return this.comprador.getUsername();
	}
	
	public Long getIdComprador() {
		return this.comprador.getId();
	}
	
	public Long getIdVendedor() {
		return this.produto.getIdVendedor();
	}
	
}
