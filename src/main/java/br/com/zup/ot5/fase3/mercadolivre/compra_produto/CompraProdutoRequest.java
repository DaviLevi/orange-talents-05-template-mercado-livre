package br.com.zup.ot5.fase3.mercadolivre.compra_produto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.ot5.fase3.mercadolivre.model.Compra;
import br.com.zup.ot5.fase3.mercadolivre.model.GatewayPagamento;
import br.com.zup.ot5.fase3.mercadolivre.model.Produto;
import br.com.zup.ot5.fase3.mercadolivre.model.Usuario;
import br.com.zup.ot5.fase3.mercadolivre.validation.ExisteEstoqueSuficiente;
import br.com.zup.ot5.fase3.mercadolivre.validation.ExisteId;
import io.jsonwebtoken.lang.Assert;

@ExisteEstoqueSuficiente
public class CompraProdutoRequest {

	@NotNull
	@ExisteId(classeDominio = Produto.class, message = "Nao existe um produto com esse id em nossa base")
	private Long idProduto;
	
	@Positive
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private GatewayPagamento metodoPagamento;

	public CompraProdutoRequest(Long idProduto, Integer quantidade, GatewayPagamento metodoPagamento) {
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.metodoPagamento = metodoPagamento;
	}
	
	public Compra paraCompra(@NotNull Produto produto, Usuario comprador) {
		Assert.state(produto != null, "Estamos tentando comprar um produto inexistente!");
		
		return new Compra(produto, comprador, this.quantidade, metodoPagamento, produto.getPreco());
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	
	
}
