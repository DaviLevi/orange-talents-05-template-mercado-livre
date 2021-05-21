package br.com.zup.ot5.fase3.mercadolivre.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnicoValidator implements ConstraintValidator<Unico, String>{

	
	private Class<?> classeDominio;
	private String nomeCampo;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void initialize(Unico constraintAnnotation) {
		this.classeDominio =  constraintAnnotation.classeDominio();
		this.nomeCampo = constraintAnnotation.nomeCampo();
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		String templateJpql = "select e from %s e where %s = '%s'";
		
		int size = manager.createQuery(String.format(templateJpql, 
				classeDominio.getName(), nomeCampo, value), classeDominio).getResultList().size();
		
		if(size == 0)
			return true;
		
		return false;
	}

}
