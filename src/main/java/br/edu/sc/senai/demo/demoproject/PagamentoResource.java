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
@RequestMapping(value = "/home")
public class PagamentoResource {

	@Autowired
	PagamentoRepository pagamentoRepository;

	@GetMapping("/pagamentos")
	public List<PagamentoEntity> listaComidasPagamento() {
		return pagamentoRepository.findAll();
	}

	@GetMapping("/pagamento/{id}")
	public PagamentoEntity listaPagamento(@PathVariable(value = "id") long id) {
		return pagamentoRepository.findById(id);
	}

	@PostMapping("/pagamento")
	public PagamentoEntity salvarPagamento(@RequestBody PagamentoEntity pagamento) {
		return pagamentoRepository.save(pagamento);
	}

	@DeleteMapping("/pagamento")
	public void deletarPagamento(@RequestBody PagamentoEntity pagamento) {
		pagamentoRepository.delete(pagamento);
	}

	@PutMapping("/pagamento")
	public PagamentoEntity atualizarPagamento(@RequestBody PagamentoEntity pagamento) {
		return pagamentoRepository.save(pagamento);
	}
}