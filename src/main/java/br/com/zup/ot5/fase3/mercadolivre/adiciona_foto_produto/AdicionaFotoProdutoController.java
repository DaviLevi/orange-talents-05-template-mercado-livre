package br.com.zup.ot5.fase3.mercadolivre.adiciona_foto_produto;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.ot5.fase3.mercadolivre.model.Produto;
import br.com.zup.ot5.fase3.mercadolivre.model.Usuario;

@RestController
@RequestMapping("/produtos/{idProduto}/fotos")
public class AdicionaFotoProdutoController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private ArmazenadorFoto armazenadorFotos;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> adicionaFoto(@PathVariable(required = true) Long idProduto, @Valid AdicionaFotoProdutoRequest requisicao, 
			Authentication authenticatedUser, UriComponentsBuilder builder) {
		 
		 Usuario usuarioLogado = (Usuario)authenticatedUser.getPrincipal();
		 
		 Produto produtoBuscado = manager.find(Produto.class, idProduto);
		 
		 if(produtoBuscado == null)
			 return ResponseEntity.notFound().build();
		 
		 if(produtoBuscado.naoPertenceAoUsuario(usuarioLogado))
			 return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		 
		 List<AdicionaFotoProdutoResponse> links = armazenadorFotos.armazena(produtoBuscado, requisicao.getFotos());
		 
		 return ResponseEntity.ok(links);
		
	}
	
	
	
	
	
	
	
	
}
