package ensalamento.dw.ensalamentodw.controller;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Produto {

	private String nome;
	private String fabricante;
	private double preco;
	private int quantidade;
	private LocalDate data;
	
	
	public Produto(String nome, String fabricante, double preco, int quantidade, LocalDate data) {
		super();
		this.nome = nome;
		this.fabricante = fabricante;
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}
	
}
