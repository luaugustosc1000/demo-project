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
public class CadastroPessoaResource {

	@Autowired
	CadastroPessoaRepository cadastroRepository;
	
	@GetMapping("/cadastros")
	public List<CadastroPessoaEntity> listaCadastros() {
		return cadastroRepository.findAll();
	}
	
	@GetMapping("/cadastro/{id}")
	public CadastroPessoaEntity listaCadastro(@PathVariable(value="id") long id) {
		return cadastroRepository.findById(id);
	}
	
	@PostMapping("/cadastro")
	public CadastroPessoaEntity salvarCadastro(@RequestBody CadastroPessoaEntity cadastro) {
		return cadastroRepository.save(cadastro);
	}
	
	@DeleteMapping("/cadastro")
	public void deletarCadastro(@RequestBody CadastroPessoaEntity cadastro) {
		cadastroRepository.delete(cadastro);
	}
	
	@PutMapping("/cadastro")
	public CadastroPessoaEntity atualizarCadastro(@RequestBody CadastroPessoaEntity cadastro) {
		return	cadastroRepository.save(cadastro);
	}		
}