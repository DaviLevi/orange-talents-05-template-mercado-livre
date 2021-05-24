package br.com.zup.ot5.fase3.mercadolivre.cadastro_produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ot5.fase3.mercadolivre.model.Produto;
import br.com.zup.ot5.fase3.mercadolivre.model.Usuario;

@RestController
@RequestMapping("/produtos")
public class CadastroProdutoController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private ProibeCaracteristicaComNomeIgualValidator validador;
	
	@InitBinder
	public void inicializador(WebDataBinder binder) {
		binder.addValidators(validador);
	}
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid CadastroProdutoRequest requisicao, Authentication authenticatedUser) {
		
		Usuario usuarioLogado = (Usuario)authenticatedUser.getPrincipal();
		
		Produto produto = requisicao.paraDominio(manager, usuarioLogado);
		
		manager.persist(produto);
	}
	
	
}
