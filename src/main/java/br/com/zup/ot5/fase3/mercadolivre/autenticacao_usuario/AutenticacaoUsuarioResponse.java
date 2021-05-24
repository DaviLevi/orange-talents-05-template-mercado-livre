package br.com.zup.ot5.fase3.mercadolivre.autenticacao_usuario;

public class AutenticacaoUsuarioResponse {

	private String tipo;
	
	private String token;

	public AutenticacaoUsuarioResponse(String tipo, String token) {
		this.tipo = tipo;
		this.token = token;
	}

	public String getTipo() {
		return tipo;
	}

	public String getToken() {
		return token;
	}
}
