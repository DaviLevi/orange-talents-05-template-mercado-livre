package br.com.zup.ot5.fase3.mercadolivre.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ExisteEstoqueSuficienteValidator.class })
public @interface ExisteEstoqueSuficiente {
	
	String message() default "O estoque do produto mencionado não é suficiente para contemplar sua compra";
	
	String nomeCampoId() default "idProduto";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
