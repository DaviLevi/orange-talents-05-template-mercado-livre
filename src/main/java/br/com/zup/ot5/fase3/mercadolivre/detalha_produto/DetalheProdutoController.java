package br.com.zup.ot5.fase3.mercadolivre.detalha_produto;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ot5.fase3.mercadolivre.model.Produto;

@RestController
@RequestMapping("/produtos/{idProduto}/detalhes")
public class DetalheProdutoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping
	public ResponseEntity<?> detalhaProduto(@PathVariable Long idProduto) {
		try {
			Produto produtoBuscado = manager.createQuery(
					"select p from Produto p "
				  + "JOIN FETCH p.caracteristicas "
				  + "JOIN FETCH p.opinioes "
				  + "JOIN FETCH p.perguntas "
				  + "JOIN FETCH p.fotosProduto "
				  + "JOIN FETCH p.categoria "
				  + "where p.id = :idProduto", Produto.class)
					.setParameter("idProduto", idProduto).getSingleResult();
			
			DetalheProdutoResponse resposta = new DetalheProdutoResponse(produtoBuscado);
			
			return ResponseEntity.ok(resposta);
			
		}catch(NoResultException exc) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
