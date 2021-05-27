package br.com.zup.ot5.fase3.mercadolivre.compra_produto_parte_2;

import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AvaliadorRankingVendedores {
	
	private final RestTemplate api = new RestTemplateBuilder().build();
	
	public void avalia(Long idCompra, Long idVendedor) {
		Map<String, Object> payload = Map.of("idCompra", idCompra, "idVendedor", idVendedor);
		
		api.postForEntity("http://localhost:8080/ranking-vendedores", payload, null);
	}
}
