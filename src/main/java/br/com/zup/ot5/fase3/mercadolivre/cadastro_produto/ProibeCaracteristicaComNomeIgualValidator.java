package br.com.zup.ot5.fase3.mercadolivre.cadastro_produto;

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
		
		if(requisicao.temCaracteristicasComNomesIguais())
			errors.rejectValue("caracteristicas", null, "As caracteristicas nao devem conter nomes duplicados");
	}
}
