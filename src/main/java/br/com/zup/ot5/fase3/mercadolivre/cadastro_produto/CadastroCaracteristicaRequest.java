package br.com.zup.ot5.fase3.mercadolivre.cadastro_produto;

import javax.validation.constraints.NotBlank;

import br.com.zup.ot5.fase3.mercadolivre.model.Caracteristica;
import br.com.zup.ot5.fase3.mercadolivre.model.Produto;

public class CadastroCaracteristicaRequest {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;
	
	public CadastroCaracteristicaRequest(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Caracteristica paraDominio(Produto produto) {
		return new Caracteristica(this.nome, this.descricao, produto);
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CadastroCaracteristicaRequest other = (CadastroCaracteristicaRequest) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
