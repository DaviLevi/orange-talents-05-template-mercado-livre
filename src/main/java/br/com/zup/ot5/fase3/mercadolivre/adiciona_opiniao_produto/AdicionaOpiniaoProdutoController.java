package br.com.zup.ot5.fase3.mercadolivre.adiciona_opiniao_produto;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ot5.fase3.mercadolivre.model.Opiniao;
import br.com.zup.ot5.fase3.mercadolivre.model.Produto;
import br.com.zup.ot5.fase3.mercadolivre.model.Usuario;

@RestController
@RequestMapping("/produtos/{idProduto}/opinioes")
public class AdicionaOpiniaoProdutoController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> adicionaOpiniao(@PathVariable Long idProduto, @RequestBody @Valid AdicionaOpiniaoProdutoRequest requisicao,
			@AuthenticationPrincipal Usuario usuarioAutenticado) {
		
		Optional<Produto> produtoOpinado = Optional.ofNullable(manager.find(Produto.class, idProduto));
		
		if(produtoOpinado.isEmpty())
			return ResponseEntity.notFound().build();
		
		Opiniao opiniao = requisicao.paraOpiniao(usuarioAutenticado, produtoOpinado.get());
		
		manager.persist(opiniao);
		
		return ResponseEntity.ok().build();
	}
}
