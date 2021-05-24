package br.com.zup.ot5.fase3.mercadolivre.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, Long>{

	@PersistenceContext
	private EntityManager manager;
	
	private Class<?> classeDominio;
	
	@Override
	public void initialize(ExisteId constraintAnnotation) {
		this.classeDominio =  constraintAnnotation.classeDominio();
	}
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		
		if(value == null)
			return false;
		
		Object entidade = manager.find(classeDominio, value);
		
		if(entidade == null) // validando se existe
			return false;
		
		return true;
		
	}

}
