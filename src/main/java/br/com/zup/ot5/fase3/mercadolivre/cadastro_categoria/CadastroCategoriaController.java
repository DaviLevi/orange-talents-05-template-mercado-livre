package br.com.zup.ot5.fase3.mercadolivre.cadastro_categoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ot5.fase3.mercadolivre.model.Categoria;

@RestController
@RequestMapping("/categorias")
public class CadastroCategoriaController {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping
	@Transactional
	public void cadastra(@RequestBody @Valid CadastroCategoriaRequest requisicao) {
		Categoria category = requisicao.toModel(em);
		em.persist(category);
	}
}
