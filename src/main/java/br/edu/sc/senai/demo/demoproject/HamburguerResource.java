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
public class HamburguerResource {

	@Autowired
	HamburguerRepository hamburguerRepository;
	
	@GetMapping("/hamburguers")
	public List<HamburguerEntity> listaComidasHamburguer() {
		return hamburguerRepository.findAll();
	}
	
	@GetMapping("/hamburguer/{id}")
	public HamburguerEntity listaHamburguer(@PathVariable(value="id") long id) {
		return hamburguerRepository.findById(id);
	}
	
	@PostMapping("/hamburguer")
	public HamburguerEntity salvarHamburguer(@RequestBody HamburguerEntity hamburguer) {
		return hamburguerRepository.save(hamburguer);
	}
	
	@DeleteMapping("/hamburguer")
	public void deletarHamburguer(@RequestBody HamburguerEntity hamburguer) {
		hamburguerRepository.delete(hamburguer);
	}
	
	@PutMapping("/hamburguer")
	public HamburguerEntity atualizarHamburguer(@RequestBody HamburguerEntity hamburguer) {
		return	hamburguerRepository.save(hamburguer);
	}		
}