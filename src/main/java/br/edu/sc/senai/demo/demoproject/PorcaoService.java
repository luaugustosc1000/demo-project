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
@RequestMapping("/porcao")
public final class PorcaoService {

	private static final PorcaoDTO[] DEFAULT_PORCOES = new PorcaoDTO[] {
			new PorcaoDTO("Batata Frita", Double.valueOf(299), Double.valueOf(299), ""),
			new PorcaoDTO("Phone Mini", Double.valueOf(299), Double.valueOf(299), ""),
			new PorcaoDTO("Phone Standard", Double.valueOf(299), Double.valueOf(299), "") };

	private final PorcaoController porcaoController;

	PorcaoService(final PorcaoController porcaoController) {
		this.porcaoController = porcaoController;
		Arrays.asList(PorcaoService.DEFAULT_PORCOES).forEach(dto -> this.porcaoController.inserirFood(dto));
	}

	@GetMapping("/comidasporcao")
	public List<PorcaoDTO> list() {
		return this.porcaoController.getAllFoods();
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<PorcaoDTO> getPorcao(@PathVariable final Long id) {
		final PorcaoDTO porcao = this.porcaoController.getFood(id);
		if (porcao.equals(PorcaoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(porcao, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PorcaoDTO> removePorcao(@PathVariable final Long id) {
		final PorcaoDTO removedPorcao = this.porcaoController.removeFood(id);
		if (removedPorcao.equals(PorcaoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedPorcao, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PorcaoDTO> updatePorcao(@PathVariable final Long id, @RequestBody final PorcaoDTO porcao) {
		final PorcaoDTO oldPorcao = this.porcaoController.atualizarPorcao(id, porcao);
		if (oldPorcao.equals(PorcaoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldPorcao, HttpStatus.OK);
	}
	
	@PostMapping
	public Long insertPorcao(@RequestBody final PorcaoDTO porcao) {
		return this.porcaoController.inserirFood(porcao);
	}
}