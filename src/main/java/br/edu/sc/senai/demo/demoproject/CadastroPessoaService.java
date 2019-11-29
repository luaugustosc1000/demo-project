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
@RequestMapping("/cadastropessoa")
public final class CadastroPessoaService {

	private static final CadastroPessoaDTO[] DEFAULT_CADASTROS = new CadastroPessoaDTO[] {
			new CadastroPessoaDTO("Luiz", "Augusto", "(47)3382-4333", "luaugustosc1000@hotmail.com", "09/03/95", "132"),
			new CadastroPessoaDTO("Richard", "Grumm", "(47)3382-4335", "rigad@hotmail.com", "24/08/00", "231"),
			new CadastroPessoaDTO("Lucas", "Puff", "(47)3382-4334", "puff@hotmail.com", "31/06/99", "123"), };

	private final CadastroPessoaController cadastroController;

	CadastroPessoaService(final CadastroPessoaController cadastroController) {
		this.cadastroController = cadastroController;
		Arrays.asList(CadastroPessoaService.DEFAULT_CADASTROS).forEach(dto -> this.cadastroController.inserirRegister(dto));
	}

	@GetMapping("/cadastropessoas")
	public List<CadastroPessoaDTO> list() {
		return this.cadastroController.getAllRegisters();
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<CadastroPessoaDTO> getCadastro(@PathVariable final Long id) {
		final CadastroPessoaDTO cadastro = this.cadastroController.getRegister(id);
		if (cadastro.equals(CadastroPessoaDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cadastro, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CadastroPessoaDTO> removeCadastro(@PathVariable final Long id) {
		final CadastroPessoaDTO removedCadastro = this.cadastroController.removeRegister(id);
		if (removedCadastro.equals(CadastroPessoaDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedCadastro, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CadastroPessoaDTO> updateCadastro(@PathVariable final Long id,
			@RequestBody final CadastroPessoaDTO cadastro) {
		final CadastroPessoaDTO oldCadastro = this.cadastroController.atualizarRegister(id, cadastro);
		if (oldCadastro.equals(CadastroPessoaDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldCadastro, HttpStatus.OK);
	}

	@PostMapping
	public Long insertCadastro(@RequestBody final CadastroPessoaDTO cadastro) {
		return this.cadastroController.inserirRegister(cadastro);
	}
}