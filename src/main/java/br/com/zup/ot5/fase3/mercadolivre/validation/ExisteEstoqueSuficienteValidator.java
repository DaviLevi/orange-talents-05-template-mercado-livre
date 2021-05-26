package br.com.zup.ot5.fase3.mercadolivre.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.ot5.fase3.mercadolivre.compra_produto.CompraProdutoRequest;
import br.com.zup.ot5.fase3.mercadolivre.model.Produto;

public class ExisteEstoqueSuficienteValidator implements ConstraintValidator<ExisteEstoqueSuficiente, CompraProdutoRequest>{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public boolean isValid(CompraProdutoRequest requisicao, ConstraintValidatorContext context) {
		
		Long idProduto = requisicao.getIdProduto();
		
		Integer quantidadeRequisitada = requisicao.getQuantidade();
		
		Produto produto = manager.find(Produto.class, idProduto);
		
		if(produto == null)
			return true;
		
		if(produto.possuiEstoquePara(quantidadeRequisitada))
			return true;
		
		return false;
	}

}
