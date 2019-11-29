package br.edu.sc.senai.demo.demoproject;

public class HamburguerDTO {
	public static final HamburguerDTO NULL_VALUE = new HamburguerDTO(Long.valueOf(0),"", Double.valueOf(0.0), Double.valueOf(0.0), "");

	private final Long id;
	private final String nome;
	private final Double quantidade;
	private final Double valor;
	private final String descricao;
	
	public HamburguerDTO(final Long id, final String nome, 
	final Double quantidade, final Double valor, final String descricao) {
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return this.nome;
	}

	public Double getQuantidade() {
		return this.quantidade;
	}

	public Double getValor() {
		return this.valor;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}