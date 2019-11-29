package br.edu.sc.senai.demo.demoproject;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public final class LoginService {

	private static final LoginDTO[] DEFAULT_LOGINS = new LoginDTO[] {
			new LoginDTO("Batata Frita", "a"),
			new LoginDTO("Phone Mini", "w"),
			new LoginDTO("Phone Standard", "s") };

	private final LoginController loginController;

	LoginService(final LoginController loginController) {
		this.loginController = loginController;
		Arrays.asList(LoginService.DEFAULT_LOGINS).forEach(dto -> this.loginController.inserirLogin(dto));
	}

	@GetMapping("/logins")
	public List<LoginDTO> list() {
		return this.loginController.getAllLogins();
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<LoginDTO> getLogin(@PathVariable final Long id) {
		final LoginDTO login = this.loginController.getLogin(id);
		if (login.equals(LoginDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(login, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<LoginDTO> removeLogin(@PathVariable final Long id) {
		final LoginDTO removedLogin = this.loginController.removeLogin(id);
		if (removedLogin.equals(LoginDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedLogin, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<LoginDTO> updateLogin(@PathVariable final Long id,
			@RequestBody final LoginDTO login) {
		final LoginDTO oldLogin = this.loginController.atualizarLogin(id, login);
		if (oldLogin.equals(LoginDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldLogin, HttpStatus.OK);
	}

	@PostMapping
	public Long insertLogin(@RequestBody final LoginDTO login) {
		return this.loginController.inserirLogin(login);
	}
}