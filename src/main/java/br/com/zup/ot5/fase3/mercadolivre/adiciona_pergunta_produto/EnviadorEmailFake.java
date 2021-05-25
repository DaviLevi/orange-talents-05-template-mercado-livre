package br.com.zup.ot5.fase3.mercadolivre.adiciona_pergunta_produto;

import org.springframework.stereotype.Component;

@Component
public class EnviadorEmailFake implements EnviadorEmail{

	@Override
	public void envia(String conteudo, String email) {
		System.out.println("Enviando um email para o vendedor " + email + "...");
		System.out.println("Corpo email : " + conteudo);
		System.out.println("Email enviada com sucesso!");
	}

}
