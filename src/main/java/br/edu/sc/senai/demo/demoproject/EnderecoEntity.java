package br.edu.sc.senai.demo.demoproject;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ENDERECO")
public final class EnderecoEntity implements Serializable {

	private static final long serialVersionUID = -4402308933349246220L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String endereco;

	private String bairro;

	private String cidade;

	private String estado;

	protected EnderecoEntity() {
	}

	public EnderecoEntity(final String endereco, final String bairro, final String cidade, final String estado) {
		this.endereco = endereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}