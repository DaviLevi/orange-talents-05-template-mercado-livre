package br.com.zup.ot5.fase3.mercadolivre.autenticacao_usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.ot5.fase3.mercadolivre.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	
	Optional<Usuario> findByLogin(String login);
	
}
