package com.login.exemplo.dto;

public class ProdutoResponseDTO {
	
	private int id;
	private String nome;
	private String preco;
	private int quantidade;
	
	
	public ProdutoResponseDTO() {
	}
	public ProdutoResponseDTO(int id, String nome, String preco, int quantidade) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	
}