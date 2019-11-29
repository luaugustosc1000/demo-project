package br.edu.sc.senai.demo.demoproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/home")
public class LoginResource {

	@Autowired
	LoginRepository loginRepository;
	
	@GetMapping("/logins")
	public List<LoginEntity> listaComidasLogin() {
		return loginRepository.findAll();
	}
	
	@GetMapping("/login/{id}")
	public LoginEntity listaLogin(@PathVariable(value="id") long id) {
		return loginRepository.findById(id);
	}
	
	@PostMapping("/login")
	public LoginEntity salvarLogin(@RequestBody LoginEntity login) {
		return loginRepository.save(login);
	}
	
	@DeleteMapping("/login")
	public void deletarLogin(@RequestBody LoginEntity login) {
		loginRepository.delete(login);
	}
	
	@PutMapping("/login")
	public LoginEntity atualizarLogin(@RequestBody LoginEntity login) {
		return	loginRepository.save(login);
	}		
}