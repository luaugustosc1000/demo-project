package br.edu.sc.senai.demo.demoproject;

public class CadastroPessoaDTO {

	public static final CadastroPessoaDTO NULL_VALUE = new CadastroPessoaDTO("", "", "", "",
	"", "");

	private final String nome;
	private final String sobrenome;
	private final String telefone;
	private final String email;
	private final String dataNascimento;
	private final String senha;
	

	public CadastroPessoaDTO(final String nome, final String sobrenome, final String telefone,
	final String email, final String dataNascimento, final String senha) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
	}


	public String getNome() {
		return nome;
	}


	public String getSobrenome() {
		return sobrenome;
	}


	public String getTelefone() {
		return telefone;
	}


	public String getEmail() {
		return email;
	}


	public String getDataNascimento() {
		return dataNascimento;
	}

	public String getSenha() {
		return senha;
	}
}