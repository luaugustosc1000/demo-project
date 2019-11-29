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
public class BuffetResource {

	@Autowired
	BuffetRepository buffetRepository;
	
	@GetMapping("/comidasbuffet")
	public List<BuffetEntity> listaComidasBuffet() {
		return buffetRepository.findAll();
	}
	
	@GetMapping("/buffet/{id}")
	public BuffetEntity listaBuffet(@PathVariable(value="id") long id) {
		return buffetRepository.findById(id);
	}
	
	@PostMapping("/buffet")
	public BuffetEntity salvarBuffet(@RequestBody BuffetEntity buffet) {
		return buffetRepository.save(buffet);
	}
	
	@DeleteMapping("/buffet")
	public void deletarBuffet(@RequestBody BuffetEntity buffet) {
		buffetRepository.delete(buffet);
	}
	
	@PutMapping("/buffet")
	public BuffetEntity atualizarBuffet(@RequestBody BuffetEntity buffet) {
		return	buffetRepository.save(buffet);
	}		
}