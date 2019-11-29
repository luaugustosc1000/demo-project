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
@RequestMapping("/pagamento")
public final class PagamentoService {

	private static final PagamentoDTO[] DEFAULT_PAGAMENTOS = new PagamentoDTO[] { new PagamentoDTO(Double.valueOf(799)),
			new PagamentoDTO(Double.valueOf(699)), new PagamentoDTO(Double.valueOf(299)) };

	private final PagamentoController pagamentoController;

	PagamentoService(final PagamentoController pagamentoController) {
		this.pagamentoController = pagamentoController;
		Arrays.asList(PagamentoService.DEFAULT_PAGAMENTOS).forEach(dto -> this.pagamentoController.inserirPayment(dto));
	}

	@GetMapping("/pagamentos")
	public List<PagamentoDTO> list() {
		return this.pagamentoController.getAllPayments();
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<PagamentoDTO> getPagamento(@PathVariable final Long id) {
		final PagamentoDTO pagamento = this.pagamentoController.getPayment(id);
		if (pagamento.equals(PagamentoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pagamento, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PagamentoDTO> removePagamento(@PathVariable final Long id) {
		final PagamentoDTO removedPagamento = this.pagamentoController.removePayment(id);
		if (removedPagamento.equals(PagamentoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedPagamento, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PagamentoDTO> updatePagamento(@PathVariable final Long id,
			@RequestBody final PagamentoDTO pagamento) {
		final PagamentoDTO oldPagamento = this.pagamentoController.atualizarPayment(id, pagamento);
		if (oldPagamento.equals(PagamentoDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldPagamento, HttpStatus.OK);
	}

	@PostMapping
	public Long insertPagamento(@RequestBody final PagamentoDTO pagamento) {
		return this.pagamentoController.inserirPayment(pagamento);
	}
}