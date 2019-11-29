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
public class PorcaoResource {

	@Autowired
	PorcaoRepository porcaoRepository;
	
	@GetMapping("/comidasporcao")
	public List<PorcaoEntity> listaComidasPorcao() {
		return porcaoRepository.findAll();
	}
	
	@GetMapping("/porcao/{id}")
	public PorcaoEntity listaPorcao(@PathVariable(value="id") long id) {
		return porcaoRepository.findById(id);
	}
	
	@PostMapping("/porcao")
	public PorcaoEntity salvarPorcao(@RequestBody PorcaoEntity porcao) {
		return porcaoRepository.save(porcao);
	}
	
	@DeleteMapping("/porcao")
	public void deletarPorcao(@RequestBody PorcaoEntity porcao) {
		porcaoRepository.delete(porcao);
	}
	
	@PutMapping("/porcao")
	public PorcaoEntity atualizarPorcao(@RequestBody PorcaoEntity porcao) {
		return	porcaoRepository.save(porcao);
	}		
}