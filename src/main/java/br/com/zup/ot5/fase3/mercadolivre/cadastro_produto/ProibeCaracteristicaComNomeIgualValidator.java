package br.com.zup.ot5.fase3.mercadolivre.cadastro_produto;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeCaracteristicaComNomeIgualValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return CadastroProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		if(errors.hasErrors())
			return;
		
		CadastroProdutoRequest requisicao = (CadastroProdutoRequest) target;
		
		Set<String> nomesIguais = requisicao.buscaCaracteristicasIguais();
		
		if(!nomesIguais.isEmpty()) {
			errors.rejectValue("caracteristicas", null, "As caracteristicas nao devem conter nomes duplicados. Exemplo : " + nomesIguais);
		}
	}
}
