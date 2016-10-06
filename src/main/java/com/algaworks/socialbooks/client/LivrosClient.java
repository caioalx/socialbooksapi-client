package com.algaworks.socialbooks.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.algaworks.socialbooks.domain.Livro;

public class LivrosClient {

	private RestTemplate template;
	
	private String URI_BASE;
	
	private String URN_BASE = "/livros";
	
	private String credencial;
	
	public LivrosClient(String url, String usuario, String senha){
		template = new RestTemplate();
		
		URI_BASE = url.concat(URN_BASE);
		
		String credencialAux = usuario.concat(":").concat(senha);
		credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());
	}
	
	public List<Livro> listar(){
		
		RequestEntity<Void> request = RequestEntity.
				get(URI.create(URI_BASE)).
				header("Authorization", credencial).build();
		
		ResponseEntity<Livro[]> response = template.exchange(request, Livro[].class);
		
		return Arrays.asList(response.getBody());
	}
	
	public String salvar(Livro livro){

		RequestEntity<Livro> request = RequestEntity.
				post(URI.create(URI_BASE)).
				header("Authorization", credencial).
				body(livro);
		
		ResponseEntity<Void> response = template.exchange(request, Void.class);
		
		return response.getHeaders().getLocation().toString();
	}
	
	public Livro buscar(String uri){
		
		RequestEntity<Void> request = RequestEntity.get(URI.create(uri)).
				header("Authorization", credencial).build();
	
		ResponseEntity<Livro> response = template.exchange(request, Livro.class);
		
		return response.getBody();
	}
	
}
