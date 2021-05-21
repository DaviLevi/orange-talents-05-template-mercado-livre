package br.com.zup.ot5.fase3.mercadolivre.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExisteEntidadeSePreenchidoValidator implements ConstraintValidator<ExisteEntidadeSePreenchido, Long>{

	@PersistenceContext
	private EntityManager manager;
	
	private Class<?> classeDominio;
	
	@Override
	public void initialize(ExisteEntidadeSePreenchido constraintAnnotation) {
		this.classeDominio =  constraintAnnotation.classeDominio();
	}
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		
		if(value == null) // se nao foi preenchido nao precisa ser valido
			return true;
		
		Object entidade = manager.find(classeDominio, value);
		
		if(entidade == null) // se foi preenchido precisa existir
			return false;
		
		return true;
		
	}

}
