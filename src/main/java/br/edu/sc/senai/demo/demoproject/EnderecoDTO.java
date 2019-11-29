package br.edu.sc.senai.demo.demoproject;

public class EnderecoDTO {
	public static final EnderecoDTO NULL_VALUE = new EnderecoDTO("", "", "", "");

	private final String endereco;
	private final String bairro;
	private final String cidade;
	private final String estado;

	public EnderecoDTO(final String endereco, final String bairro, final String cidade,
	final String estado) {
		this.endereco = endereco;		
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}	
}