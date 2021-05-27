package br.com.zup.ot5.fase3.mercadolivre.model;

import java.time.LocalDateTime;

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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;


@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TransacaoID")
	private Long id;
	
	@NotNull
	@Column(name = "IdTransacaoGateway")
	private String idTransacaoGateway;
	
	@NotNull
	@Column(name = "StatusTransacao")
	@Enumerated(EnumType.STRING)
	private StatusTransacao statusTransacao;
	
	@CreationTimestamp
	@Column(name = "Instante")
	private LocalDateTime instante;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "CompraID")
	@NotNull
	@Valid
	private Compra compra;

	@Deprecated public Transacao() {}
	
	
	public Transacao(@NotNull String idTransacaoGateway, @NotNull StatusTransacao statusTransacao,
			@NotNull @Valid Compra compra) {
		super();
		this.idTransacaoGateway = idTransacaoGateway;
		this.statusTransacao = statusTransacao;
		this.compra = compra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTransacaoGateway == null) ? 0 : idTransacaoGateway.hashCode());
		return result;
	}
	
	
	public boolean concluidaComSucesso() {
		return this.statusTransacao == StatusTransacao.SUCESSO;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (idTransacaoGateway == null) {
			if (other.idTransacaoGateway != null)
				return false;
		} else if (!idTransacaoGateway.equals(other.idTransacaoGateway))
			return false;
		return true;
	}
	
}
