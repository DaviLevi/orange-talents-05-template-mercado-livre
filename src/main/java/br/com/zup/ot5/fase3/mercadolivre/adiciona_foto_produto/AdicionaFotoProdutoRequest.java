package br.com.zup.ot5.fase3.mercadolivre.adiciona_foto_produto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class AdicionaFotoProdutoRequest {

	@Size(min = 1)
	@NotNull
	private List<@NotNull MultipartFile> fotos;
	
	public AdicionaFotoProdutoRequest(List<MultipartFile> fotos) {
		this.fotos = fotos;
	}
	
	public List<MultipartFile> getFotos(){
		return this.fotos;
	}
}
