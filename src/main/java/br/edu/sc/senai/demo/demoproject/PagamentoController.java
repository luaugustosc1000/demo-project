package br.edu.sc.senai.demo.demoproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
final class PagamentoController {

	private final PagamentoRepository pagamentoRepository;

	PagamentoController(final PagamentoRepository pagamentoRepository) {
		this.pagamentoRepository = pagamentoRepository;
	}

	private static void atualizarEntityFromDTO(final PagamentoDTO pagamentoDTO, final PagamentoEntity pagamentoEntity) {
		pagamentoEntity.setValor(pagamentoDTO.getValor());
	}

	private static PagamentoEntity toEntity(final PagamentoDTO pagamentoDTO) {
		final Double valor = pagamentoDTO.getValor();
		return new PagamentoEntity(valor);
	}

	private static PagamentoDTO toDTO(final PagamentoEntity pagamentoEntity) {
		final Double valor = pagamentoEntity.getValor();
		return new PagamentoDTO(valor);
	}

	List<PagamentoDTO> getAllPayments() {
		final List<PagamentoDTO> pagamentos = new ArrayList<>();
		this.pagamentoRepository.findAll()
				.forEach(pagamentoEntity -> pagamentos.add(PagamentoController.toDTO(pagamentoEntity)));
		/*
		 * final Iterable<ProductEntity> entities = this.productRepository.findAll();
		 * for (final ProductEntity productEntity : entities) {
		 * products.add(ProductController.toDTO(productEntity)); }
		 */
		/*
		 * for (final Iterator<ProductEntity> iterator = entities.iterator();
		 * iterator.hasNext();) { final ProductEntity productEntity = iterator.next();
		 * products.add(ProductController.toDTO(productEntity)); }
		 */
		return pagamentos;
	}

	PagamentoDTO getPayment(final Long id) {
		final Optional<PagamentoEntity> optionalPagamento = this.pagamentoRepository.findById(id);
		if (optionalPagamento.isPresent()) {
			return PagamentoController.toDTO(optionalPagamento.get());
		}
		return PagamentoDTO.NULL_VALUE;
	}

	PagamentoDTO removePayment(final Long id) {
		final Optional<PagamentoEntity> optionalPagamento = this.pagamentoRepository.findById(id);
		if (optionalPagamento.isPresent()) {
			final PagamentoEntity pagamentoEntity = optionalPagamento.get();
			this.pagamentoRepository.delete(pagamentoEntity);
			return PagamentoController.toDTO(pagamentoEntity);
		}
		return PagamentoDTO.NULL_VALUE;
	}

	Long inserirPayment(final PagamentoDTO pagamentoDTO) {
		final PagamentoEntity pagamentoEntity = PagamentoController.toEntity(pagamentoDTO);
		this.pagamentoRepository.save(pagamentoEntity);
		return pagamentoEntity.getId();
	}

	PagamentoDTO atualizarPayment(final Long id, final PagamentoDTO pagamentoDTO) {
		final Optional<PagamentoEntity> optionalPagamento = this.pagamentoRepository.findById(id);
		if (optionalPagamento.isPresent()) {
			final PagamentoEntity pagamentoEntity = optionalPagamento.get();
			final PagamentoDTO oldPagamentoDTO = PagamentoController.toDTO(pagamentoEntity);
			PagamentoController.atualizarEntityFromDTO(pagamentoDTO, pagamentoEntity);
			this.pagamentoRepository.save(pagamentoEntity);
			return oldPagamentoDTO;
		}
		return PagamentoDTO.NULL_VALUE;
	}
}