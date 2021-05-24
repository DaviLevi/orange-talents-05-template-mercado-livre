package br.com.zup.ot5.fase3.mercadolivre.autenticacao_usuario;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private GerenciadorToken tokenService;
	
	@PostMapping
	public ResponseEntity<AutenticacaoUsuarioResponse> autenticar(@RequestBody @Valid AutenticacaoUsuarioRequest requisicao){
		UsernamePasswordAuthenticationToken dadosLogin = requisicao.paraSpringSecurityUserPasswordAuth();
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			
			String token = tokenService.gerarToken(authentication);
			
			return ResponseEntity.ok(new AutenticacaoUsuarioResponse("Bearer",token));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
