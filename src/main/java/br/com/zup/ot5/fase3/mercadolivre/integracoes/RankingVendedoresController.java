package br.com.zup.ot5.fase3.mercadolivre.integracoes;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ranking-vendedores")
public class RankingVendedoresController {

	@PostMapping
	public ResponseEntity<?> geraNotaFiscal(@Valid @RequestBody RankingVendedoresRequest requisicao) {
		
		System.out.println(requisicao);
		
		return ResponseEntity.ok().build();
	}
	
	
	
	public static class RankingVendedoresRequest{
		
		private Long idCompra;
		private Long idVendedor;
		
		public RankingVendedoresRequest(Long idCompra, Long idVendedor) {
			this.idCompra = idCompra;
			this.idVendedor = idVendedor;
		}

		@Override
		public String toString() {
			return "Ranking vendedor gerado para : [idCompra=" + idCompra + ", idVendedor=" + idVendedor + "]";
		}
	}
	
}
