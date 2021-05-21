package br.com.zup.ot5.fase3.mercadolivre.cadastro_usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.ot5.fase3.mercadolivre.model.Usuario;
import br.com.zup.ot5.fase3.mercadolivre.validation.Unico;

public class CadastroUsuarioRequest {
	
	@NotBlank
	@Email
	@Unico(classeDominio = Usuario.class, nomeCampo = "login")
	private String login;
	
	@Size(min = 6, max = 59)
	@NotBlank
	private String senha;

	public CadastroUsuarioRequest(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario toModel() {
		return new Usuario(login, senha);
	}
}
