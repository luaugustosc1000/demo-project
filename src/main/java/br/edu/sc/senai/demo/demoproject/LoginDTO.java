package br.edu.sc.senai.demo.demoproject;

public class LoginDTO {
	public static final LoginDTO NULL_VALUE = new LoginDTO("", "");

	private final String login;
	private final String senha;

	public LoginDTO(final String login, final String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return this.login;
	}

	public String getSenha() {
		return this.senha;
	}
}