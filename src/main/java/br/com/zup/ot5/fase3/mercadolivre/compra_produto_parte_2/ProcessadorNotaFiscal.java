package br.com.zup.ot5.fase3.mercadolivre.compra_produto_parte_2;

import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProcessadorNotaFiscal {

	
	private final RestTemplate api = new RestTemplateBuilder().build();
	
	public void processa(Long idCompra, Long idComprador) {
		Map<String, Object> payload = Map.of("idCompra", idCompra, "idUsuario", idComprador);
		
		api.postForEntity("http://localhost:8080/nota-fiscal", payload, String.class);
	}
	
	
}
