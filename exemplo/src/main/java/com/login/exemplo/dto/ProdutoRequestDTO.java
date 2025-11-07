package com.login.exemplo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProdutoRequestDTO {
	

	@NotNull(message = "Nome do produto")
	private String nome;
	
	@NotBlank(message = "Preco do produto")
	private int preco;
	
	@NotNull(message = "Quantidade do produto" )
	private int quantidade;
	
	public ProdutoRequestDTO() {
		
	}

	public ProdutoRequestDTO(@NotNull(message = "Nome do produto") String nome,
			@NotBlank(message = "Preco do produto") int preco,
			@NotNull(message = "Quantidade do produto") int quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
