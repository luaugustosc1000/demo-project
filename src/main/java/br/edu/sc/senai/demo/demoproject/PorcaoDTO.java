package br.edu.sc.senai.demo.demoproject;

public class PorcaoDTO {
	public static final PorcaoDTO NULL_VALUE = new PorcaoDTO("", Double.valueOf(0.0),
	Double.valueOf(0.0), "");

	private final String nome;
	private final Double quantidade;
	private final Double valor;
	private final String descricao;
	
	public PorcaoDTO(final String nome, final Double quantidade,
	final Double valor, final String descricao) {
		this.nome = nome;		
		this.quantidade = quantidade;
		this.valor = valor;
		this.descricao = descricao;
	}

	public String getNome() {
		return this.nome;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public Double getValor() {
		return this.valor;
	}
	
	public String getDescricao() {
		return descricao;
	}
}