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
@RequestMapping(value = "/home")
public class EnderecoResource {

	@Autowired
	EnderecoRepository enderecoRepository;

	@GetMapping("/enderecos")
	public List<EnderecoEntity> listaEnderecos() {
		return enderecoRepository.findAll();
	}

	@GetMapping("/endereco/{id}")
	public EnderecoEntity listaEndereco(@PathVariable(value = "id") long id) {
		return enderecoRepository.findById(id);
	}

	@PostMapping("/endereco")
	public EnderecoEntity salvarEndereco(@RequestBody EnderecoEntity endereco) {
		return enderecoRepository.save(endereco);
	}

	@DeleteMapping("/endereco")
	public void deletarEndereco(@RequestBody EnderecoEntity endereco) {
		enderecoRepository.delete(endereco);
	}

	@PutMapping("/endereco")
	public EnderecoEntity atualizarEndereco(@RequestBody EnderecoEntity endereco) {
		return enderecoRepository.save(endereco);
	}
}