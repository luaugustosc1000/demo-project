package br.edu.sc.senai.demo.demoproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
	final class PorcaoController {

		private final PorcaoRepository porcaoRepository;

		PorcaoController(final PorcaoRepository porcaoRepository) {
			this.porcaoRepository = porcaoRepository;
		}

		private static void atualizarEntityFromDTO(final PorcaoDTO porcaoDTO, final PorcaoEntity porcaoEntity) {
			porcaoEntity.setNome(porcaoDTO.getNome());
			porcaoEntity.setQuantidade(porcaoDTO.getQuantidade());
			porcaoEntity.setValor(porcaoDTO.getValor());
			porcaoEntity.setDescricao(porcaoDTO.getDescricao());
		}

		private static PorcaoEntity toEntity(final PorcaoDTO porcaoDTO) {
			final String nome = porcaoDTO.getNome();
			final Double quantidade = porcaoDTO.getQuantidade();
			final Double valor = porcaoDTO.getValor();
			final String descricao = porcaoDTO.getDescricao();
			return new PorcaoEntity(nome, quantidade, valor, descricao);
		}

		private static PorcaoDTO toDTO(final PorcaoEntity porcaoEntity) {
			final String nome = porcaoEntity.getNome();
			final Double quantidade = porcaoEntity.getQuantidade();
			final Double valor = porcaoEntity.getValor();
			final String descricao = porcaoEntity.getDescricao();
			return new PorcaoDTO(nome, quantidade, valor, descricao);
		}

		List<PorcaoDTO> getAllFoods() {
			final List<PorcaoDTO> comidas = new ArrayList<>();
			this.porcaoRepository.findAll().forEach(porcaoEntity -> comidas.add(PorcaoController.toDTO(porcaoEntity)));
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
			return comidas;
		}

		PorcaoDTO getFood(final Long id) {
			final Optional<PorcaoEntity> optionalPorcao = this.porcaoRepository.findById(id);
			if (optionalPorcao.isPresent()) {
				return PorcaoController.toDTO(optionalPorcao.get());
			}
			return PorcaoDTO.NULL_VALUE;
		}

		PorcaoDTO removeFood(final Long id) {
			final Optional<PorcaoEntity> optionalPorcao = this.porcaoRepository.findById(id);
			if (optionalPorcao.isPresent()) {
				final PorcaoEntity porcaoEntity = optionalPorcao.get();
				this.porcaoRepository.delete(porcaoEntity);
				return PorcaoController.toDTO(porcaoEntity);
			}
			return PorcaoDTO.NULL_VALUE;
		}

		Long inserirFood(final PorcaoDTO porcaoDTO) {
			final PorcaoEntity porcaoEntity = PorcaoController.toEntity(porcaoDTO);
			this.porcaoRepository.save(porcaoEntity);
			return porcaoEntity.getId();
		}

		PorcaoDTO atualizarPorcao(final Long id, final PorcaoDTO porcaoDTO) {
			final Optional<PorcaoEntity> optionalPorcao = this.porcaoRepository.findById(id);
			if (optionalPorcao.isPresent()) {
				final PorcaoEntity porcaoEntity = optionalPorcao.get();
				final PorcaoDTO oldPorcaoDTO = PorcaoController.toDTO(porcaoEntity);
				PorcaoController.atualizarEntityFromDTO(porcaoDTO, porcaoEntity);
				this.porcaoRepository.save(porcaoEntity);
				return oldPorcaoDTO;
			}
			return PorcaoDTO.NULL_VALUE;
		}
	}