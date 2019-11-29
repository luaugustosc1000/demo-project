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
@RequestMapping("/buffet")
public final class BuffetService {

	private static final BuffetDTO[] DEFAULT_BUFFETS = new BuffetDTO[] {
			new BuffetDTO("Couve Flor"),
			new BuffetDTO("Alface"),
			new BuffetDTO("Brocólis"),
			new BuffetDTO("Pepino"),
			new BuffetDTO("Tomate"),
			new BuffetDTO("Beterraba"),
			new BuffetDTO("Salada de Maionese"),
			new BuffetDTO("Arroz"),
			new BuffetDTO("Arroz Integral"),
			new BuffetDTO("Macarrão"),
			new BuffetDTO("Feijão Preto"),
			new BuffetDTO("Carne de Panela"),
			new BuffetDTO("Bife"),
			new BuffetDTO("Coração de Frango"),
			new BuffetDTO("Frango a Milanesa"),
			new BuffetDTO("Peito de Frango"),
			new BuffetDTO("Filé de Peixe"),
			new BuffetDTO("Mandioca") };

	private final BuffetController buffetController;

	BuffetService(final BuffetController buffetController) {
		this.buffetController = buffetController;
		Arrays.asList(BuffetService.DEFAULT_BUFFETS).forEach(dto -> this.buffetController.inserirFood(dto));
	}

	@GetMapping("/comidasbuffet")
	public List<BuffetDTO> list() {
		return this.buffetController.getAllFoods();
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<BuffetDTO> getBuffet(@PathVariable final Long id) {
		final BuffetDTO buffet = this.buffetController.getFood(id);
		if (buffet.equals(BuffetDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(buffet, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BuffetDTO> removeBuffet(@PathVariable final Long id) {
		final BuffetDTO removedBuffet = this.buffetController.removeFood(id);
		if (removedBuffet.equals(BuffetDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedBuffet, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BuffetDTO> updateBuffet(@PathVariable final Long id,
			@RequestBody final BuffetDTO buffet) {
		final BuffetDTO oldBuffet = this.buffetController.atualizarBuffet(id, buffet);
		if (oldBuffet.equals(BuffetDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldBuffet, HttpStatus.OK);
	}

	@PostMapping
	public Long insertBuffet(@RequestBody final BuffetDTO buffet) {
		return this.buffetController.inserirFood(buffet);
	}
}