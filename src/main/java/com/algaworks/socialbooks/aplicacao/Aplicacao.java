package com.algaworks.socialbooks.aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.algaworks.socialbooks.client.LivrosClient;
import com.algaworks.socialbooks.domain.Livro;

public class Aplicacao {

	public static void main(String[] args) throws ParseException {
		LivrosClient cliente = new LivrosClient("http://localhost:8080", "algaworks", "senha");
		
		List<Livro> livros = cliente.listar();
		
		for(Livro livro : livros){
			System.out.println("Livro: " + livro.getNome());
		}
		
		Livro livro = new Livro();
		
		livro.setNome("Rest Aplicado");
		livro.setEditora("NascSilva");
		livro.setResumo("Livro sobre o desenvolvimento de API's REST.");

		SimpleDateFormat publicacao = new SimpleDateFormat("dd/MM/yyyy");
		livro.setPublicacao(publicacao.parse("10/10/2010"));

		String localizacao = cliente.salvar(livro);
		
		System.out.println("URI do livro salvo: " + localizacao);
	
		Livro livroBuscado = cliente.buscar(localizacao);
		
		System.out.println("Livro buscado: " + livroBuscado.getNome());		
	}
	
}
