package br.com.zup.ot5.fase3.mercadolivre.adiciona_foto_produto;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.zup.ot5.fase3.mercadolivre.model.Produto;

@Component
public class ArmazenadorFotoS3 implements ArmazenadorFoto{

	@Override
	public List<AdicionaFotoProdutoResponse> armazena(Produto produto, List<MultipartFile> dadosFoto) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
