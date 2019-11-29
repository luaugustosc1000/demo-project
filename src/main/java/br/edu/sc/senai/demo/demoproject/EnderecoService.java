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
@RequestMapping("/endereco")
public final class EnderecoService {

	private static final EnderecoDTO[] DEFAULT_ENDERECOS = new EnderecoDTO[] {
			new EnderecoDTO("Luiz", "Augusto", "(47)3382-4333", "luaugustosc1000@hotmail.com"),
			new EnderecoDTO("Luiz", "Augusto", "(47)3382-4333", "luaugustosc1000@hotmail.com"),
			new EnderecoDTO("Luiz", "Augusto", "(47)3382-4333", "luaugustosc1000@hotmail.com"),
			new EnderecoDTO("Luiz", "Augusto", "(47)3382-4333", "luaugustosc1000@hotmail.com"),
			new EnderecoDTO("Luiz", "Augusto", "(47)3382-4333", "luaugustosc1000@hotmail.com"),
			new EnderecoDTO("Luiz", "Augusto", "(47)3382-4333", "luaugustosc1000@hotmail.com"),
			new EnderecoDTO("Rigad", "Viado", "(24)2424-2424", "euricorigad@hotmail.com"),
			new EnderecoDTO("Lucas", "Puff", "(47)3382-4334", "puff@hotmail.com"), };

	private final EnderecoController enderecoController;

	EnderecoService(final EnderecoController enderecoController) {
		this.enderecoController = enderecoController;
		Arrays.asList(EnderecoService.DEFAULT_ENDERECOS).forEach(dto -> this.enderecoController.inserirAdress(dto));
	}

	@GetMapping("/enderecos")
	public List<EnderecoDTO> list() {
		return this.enderecoController.getAllAdresses();
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<EnderecoDTO> getEndereco(@PathVariable final Long id) {
		final EnderecoDTO endereco = this.enderecoController.getAdress(id);
		if (endereco.equals(EnderecoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(endereco, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<EnderecoDTO> removeEndereco(@PathVariable final Long id) {
		final EnderecoDTO removedEndereco = this.enderecoController.removeAdress(id);
		if (removedEndereco.equals(EnderecoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedEndereco, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EnderecoDTO> updateEndereco(@PathVariable final Long id,
			@RequestBody final EnderecoDTO endereco) {
		final EnderecoDTO oldEndereco = this.enderecoController.atualizarAdress(id, endereco);
		if (oldEndereco.equals(EnderecoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldEndereco, HttpStatus.OK);
	}

	@PostMapping
	public Long insertEndereco(@RequestBody final EnderecoDTO endereco) {
		return this.enderecoController.inserirAdress(endereco);
	}
}