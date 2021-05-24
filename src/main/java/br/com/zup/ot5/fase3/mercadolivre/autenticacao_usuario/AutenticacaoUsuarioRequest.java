package br.com.zup.ot5.fase3.mercadolivre.autenticacao_usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AutenticacaoUsuarioRequest {

	@NotBlank
	@Email
	private String login;
	
	@NotBlank
	private String senha;

	public AutenticacaoUsuarioRequest(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public UsernamePasswordAuthenticationToken paraSpringSecurityUserPasswordAuth() {
		return new UsernamePasswordAuthenticationToken(login, senha);
	}
	
	
}
