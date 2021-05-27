package br.com.zup.ot5.fase3.mercadolivre.compra_produto_parte_2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ot5.fase3.mercadolivre.adiciona_pergunta_produto.EnviadorEmail;
import br.com.zup.ot5.fase3.mercadolivre.model.Compra;
import br.com.zup.ot5.fase3.mercadolivre.model.Transacao;

@RestController
public class FinalizaCompraController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private EnviadorEmail enviadorEmailFake;

	@Autowired
	private ProcessadorNotaFiscal processadorNF;
	
	@Autowired
	private AvaliadorRankingVendedores avaliador;
	
	
	@PostMapping
	@RequestMapping("/retorno-pagseguro/{idCompra}")
	@Transactional
	public ResponseEntity<?> finalizaCompraPagSeguro(@PathVariable(required = true) Long idCompra
			, @Valid RetornoGatewayPagseguroRequest requisicao) {
		
		processa(idCompra, requisicao);
		
		return ResponseEntity.ok().build();
	}
	
	
	@PostMapping
	@RequestMapping("/retorno-paypal/{idCompra}")
	@Transactional
	public ResponseEntity<?> finalizaCompraPaypal(@PathVariable(required = true) Long idCompra, @Valid RetornoGatewayPaypalRequest requisicao) {
		
		processa(idCompra, requisicao);
		
		return ResponseEntity.ok().build();
	}

	
	
	private void processa(Long idCompra, RetornoGatewayPagamentoRequest requisicao) {
		Compra compraBuscada = manager.find(Compra.class, idCompra);
		
		Transacao transacao = requisicao.paraTransacao(compraBuscada);
		
		compraBuscada.adicionaTransacao(transacao);
		
		manager.merge(compraBuscada);
		
		if(transacao.concluidaComSucesso()) {
			
			processadorNF.processa(idCompra, compraBuscada.getIdComprador());
			
			avaliador.avalia(idCompra, compraBuscada.getIdVendedor());
			
			enviadorEmailFake.envia("Parabens, a sua compra foi concluida com sucesso!", compraBuscada.getEmailComprador());
			
		}else {
			
			enviadorEmailFake.envia("Ops! A sua tentativa de pagamento falhou. Por favor, tente mais tarde", compraBuscada.getEmailComprador());
			
		}
	}
	
}
