package br.com.zup.ot5.fase3.mercadolivre.compra_produto_parte_1;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ot5.fase3.mercadolivre.adiciona_pergunta_produto.EnviadorEmail;
import br.com.zup.ot5.fase3.mercadolivre.model.Compra;
import br.com.zup.ot5.fase3.mercadolivre.model.Produto;
import br.com.zup.ot5.fase3.mercadolivre.model.Usuario;

@RestController
@RequestMapping("/compras")
public class CompraProdutoController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private ProcessadorPagamento processadorPagamento;
	
	@Autowired
	private EnviadorEmail enviadorEmailFake;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> compraProduto(@RequestBody @Valid CompraProdutoRequest requisicao, @AuthenticationPrincipal Usuario comprador) {
		
		Produto produtoBuscado = manager.find(Produto.class, requisicao.getIdProduto());
		
		Compra compra = requisicao.paraCompra(produtoBuscado, comprador);
		
		manager.persist(compra);
		
		enviadorEmailFake.envia("O cliente com email '" + comprador.getUsername() + "' esta querendo comprar seu produto", produtoBuscado.getEmailVendedor());
		
		produtoBuscado.abateEstoque(requisicao.getQuantidade());
		
		String urlDirecionamento = processadorPagamento.processaPagamento(compra);
		
		return ResponseEntity.status(HttpStatus.FOUND).header("Location", urlDirecionamento).build();
		
	}
	
	
}
