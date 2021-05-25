package br.com.zup.ot5.fase3.mercadolivre.adiciona_pergunta_produto;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ot5.fase3.mercadolivre.model.Pergunta;
import br.com.zup.ot5.fase3.mercadolivre.model.Produto;
import br.com.zup.ot5.fase3.mercadolivre.model.Usuario;

@RestController
@RequestMapping("/produtos/{idProduto}/perguntas")
public class AdicionaPerguntaProdutoController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private EnviadorEmail enviador;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> adicionaPergunta(@PathVariable Long idProduto, @RequestBody @Valid AdicionaPerguntaProdutoRequest requisicao,
			@AuthenticationPrincipal Usuario usuarioAutenticado) {
		
		Optional<Produto> produtoQuestionado = Optional.ofNullable(manager.find(Produto.class, idProduto));
		
		if(produtoQuestionado.isEmpty())
			return ResponseEntity.notFound().build();
		
		Pergunta pergunta = requisicao.paraPergunta(usuarioAutenticado, produtoQuestionado.get());
		
		manager.persist(pergunta);
		
		enviador.envia(requisicao.getTitulo(), produtoQuestionado.get().getEmailVendedor());
		
		return ResponseEntity.ok().build();
	}
}