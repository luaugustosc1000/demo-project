package br.edu.sc.senai.demo.demoproject;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_CADASTRO")
public final class CadastroPessoaEntity implements Serializable {

	private static final long serialVersionUID = -4402308933349246220L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	private String sobrenome;
	
	private String telefone;
	
	private String email;
	
	private String dataNascimento;
	
	private  String senha;
	
	protected CadastroPessoaEntity() {
	}
	
	public CadastroPessoaEntity(final String nome, final String sobrenome, final String telefone, 
	final String email, final String dataNascimento, final String senha) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.senha = "";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}