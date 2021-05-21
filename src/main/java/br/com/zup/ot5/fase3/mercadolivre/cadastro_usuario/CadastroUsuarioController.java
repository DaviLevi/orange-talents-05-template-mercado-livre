package br.com.zup.ot5.fase3.mercadolivre.cadastro_usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.ot5.fase3.mercadolivre.model.Usuario;

@RestController
@RequestMapping("/usuarios")
public class CadastroUsuarioController {

	
	@PersistenceContext
	private EntityManager em;
	
	
	@PostMapping
	@Transactional
	public void cadastra(@RequestBody @Valid CadastroUsuarioRequest requisicao) {
		Usuario user = requisicao.toModel();
		em.persist(user);
	}
	
	
}
