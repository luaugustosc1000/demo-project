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
@RequestMapping("/hamburguer")
public final class HamburguerService {

	private static final HamburguerDTO[] DEFAULT_HAMBURGUERS = new HamburguerDTO[] {
			new HamburguerDTO(Long.valueOf(0),"Batata Frita", Double.valueOf(299), Double.valueOf(799), ""),
			new HamburguerDTO(Long.valueOf(1),"cedilha hamburguer", Double.valueOf(299), Double.valueOf(699), ""),
			new HamburguerDTO(Long.valueOf(2),"xis hamburger", Double.valueOf(299), Double.valueOf(299), "") };

	private final HamburguerController hamburguerController;

	HamburguerService(final HamburguerController hamburguerController) {
		this.hamburguerController = hamburguerController;
		Arrays.asList(HamburguerService.DEFAULT_HAMBURGUERS).forEach(dto -> this.hamburguerController.inserirFood(dto));
	}

	@GetMapping("/hamburguers")
	public List<HamburguerDTO> list() {
		return this.hamburguerController.getAllFoods();
	}

	@GetMapping("/{id}")
	public ResponseEntity<HamburguerDTO> getHamburguer(@PathVariable final Long id) {
		final HamburguerDTO hamburguer = this.hamburguerController.getFood(id);
		if (hamburguer.equals(HamburguerDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(hamburguer, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HamburguerDTO> removeHamburguer(@PathVariable final Long id) {
		final HamburguerDTO removedHamburguer = this.hamburguerController.removeFood(id);
		if (removedHamburguer.equals(HamburguerDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedHamburguer, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<HamburguerDTO> updateHamburguer(@PathVariable final Long id,
			@RequestBody final HamburguerDTO hamburguer) {
		final HamburguerDTO oldHamburguer = this.hamburguerController.atualizarHamburguer(id, hamburguer);
		if (oldHamburguer.equals(HamburguerDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldHamburguer, HttpStatus.OK);
	}

	@PostMapping
	public Long insertHamburguer(@RequestBody final HamburguerDTO hamburguer) {
		return this.hamburguerController.inserirFood(hamburguer);
	}
}