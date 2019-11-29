package br.edu.sc.senai.demo.demoproject;

public class BuffetDTO {
	public static final BuffetDTO NULL_VALUE = new BuffetDTO("");

	private final String nome;	

	public BuffetDTO(final String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}
}