package br.com.zup.ot5.fase3.mercadolivre.adiciona_foto_produto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.zup.ot5.fase3.mercadolivre.model.Produto;

public interface ArmazenadorFoto {

	List<AdicionaFotoProdutoResponse> armazena(Produto produto, List<MultipartFile> dadosFoto);
}
