package br.com.zup.ot5.fase3.mercadolivre.adiciona_foto_produto;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.ot5.fase3.mercadolivre.model.FotoProduto;
import br.com.zup.ot5.fase3.mercadolivre.model.Produto;
import io.jsonwebtoken.lang.Assert;

@Component
@Primary
public class ArmazenadorFotoFicticio implements ArmazenadorFoto{

	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public List<AdicionaFotoProdutoResponse> armazena(Produto produto, List<MultipartFile> dadosFoto) {
		
		Assert.isTrue(dadosFoto != null, "Está sendo sincronizada ao produto uma lista nula!");
		Assert.isTrue(dadosFoto.size() >= 1, "Está sendo sincronizada ao produto uma lista de tamanho menor que 1!");
		
		Set<FotoProduto> fotosProduto = dadosFoto.stream().map(mpf -> {
			String uuid = UUID.randomUUID().toString();
			String url = UriComponentsBuilder.fromHttpUrl("https://www.s3amazon.com.br/fotos/{uuid}").buildAndExpand(uuid).toUriString();
			return new FotoProduto(url, mpf.getOriginalFilename(), produto);
		}).collect(Collectors.toSet());
		
		produto.adicionaFotosProduto(fotosProduto);
		
		manager.merge(produto);
		
		return fotosProduto.stream().map(AdicionaFotoProdutoResponse::new).collect(Collectors.toList());
	}
	
	

}
