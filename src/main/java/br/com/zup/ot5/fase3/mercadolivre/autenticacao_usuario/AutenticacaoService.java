package br.com.zup.ot5.fase3.mercadolivre.autenticacao_usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zup.ot5.fase3.mercadolivre.model.Usuario;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optional = repository.findByLogin(username);
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new UsernameNotFoundException("Dados Invalidos");
		
	}

}
