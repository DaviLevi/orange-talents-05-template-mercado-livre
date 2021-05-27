package br.com.zup.ot5.fase3.mercadolivre.integracoes;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nota-fiscal")
public class NotaFiscalController {

	@PostMapping
	public ResponseEntity<?> geraNotaFiscal(@Valid @RequestBody NotaFiscalRequest requisicao) {
		
		System.out.println(requisicao);
		
		return ResponseEntity.ok().build();
	}
	
	
	
	public static class NotaFiscalRequest{
		
		private Long idCompra;
		private Long idUsuario;
		
		public NotaFiscalRequest(Long idCompra, Long idUsuario) {
			this.idCompra = idCompra;
			this.idUsuario = idUsuario;
		}

		@Override
		public String toString() {
			return "NotaFiscal gerada para : [idCompra=" + idCompra + ", idUsuario=" + idUsuario + "]";
		}
	}
	
}
