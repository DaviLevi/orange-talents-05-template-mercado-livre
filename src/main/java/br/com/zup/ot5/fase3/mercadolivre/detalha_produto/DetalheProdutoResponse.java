package br.com.zup.ot5.fase3.mercadolivre.detalha_produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.zup.ot5.fase3.mercadolivre.model.Produto;

public class DetalheProdutoResponse {
	
	private List<DetalheProdutoURLFotoProdutoResponse> urls;
	
	private String nome;
	
	private BigDecimal preco;
	
	private List<DetalheProdutoCaracteristicaResponse> caracteristicas;
	
	private String descricao;
	
	private Long quantidadeNotas;
	
	private Double mediaNotas;
	
	private Map<Integer, Long> quantidadeOpinioesPorNota;
	
	private List<DetalheProdutoOpiniaoResponse> opinioes; 
	
	private List<DetalheProdutoPerguntaResponse> perguntas; 

	
	public DetalheProdutoResponse(Produto produto) {
		this.mediaNotas = produto.calculaMediaNotas();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.nome = produto.getNome();
		this.quantidadeOpinioesPorNota = produto.calculaQuantidadeOpinioesPorNota();
		this.urls = produto.getFotosProduto().stream().map(DetalheProdutoURLFotoProdutoResponse::new).collect(Collectors.toList());
		this.caracteristicas = produto.getCaracteristicas().stream().map(DetalheProdutoCaracteristicaResponse::new).collect(Collectors.toList());
		this.opinioes = produto.getOpinioes().stream().map(DetalheProdutoOpiniaoResponse::new).collect(Collectors.toList());
		this.perguntas = produto.getPerguntas().stream().map(DetalheProdutoPerguntaResponse::new).collect(Collectors.toList());
		this.quantidadeNotas = produto.calculaQuantidadeOpinioes();
	}

	public List<DetalheProdutoURLFotoProdutoResponse> getUrls() {
		return urls;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public List<DetalheProdutoCaracteristicaResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getMediaNotas() {
		return mediaNotas;
	}

	public List<DetalheProdutoOpiniaoResponse> getOpinioes() {
		return opinioes;
	}

	public List<DetalheProdutoPerguntaResponse> getPerguntas() {
		return perguntas;
	}

	public Long getQuantidadeNotas() {
		return quantidadeNotas;
	}

	public Map<Integer, Long> getQuantidadeOpinioesPorNota() {
		return quantidadeOpinioesPorNota;
	}
	
	
}
