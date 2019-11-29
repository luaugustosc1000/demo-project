package br.edu.sc.senai.demo.demoproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
final class HamburguerController {

	private final HamburguerRepository hamburguerRepository;

	HamburguerController(final HamburguerRepository hamburguerRepository) {
		this.hamburguerRepository = hamburguerRepository;
	}

	private static void atualizarEntityFromDTO(final HamburguerDTO hamburguerDTO, final HamburguerEntity hamburguerEntity) {
		hamburguerEntity.setNome(hamburguerDTO.getNome());
		hamburguerEntity.setQuantidade(hamburguerDTO.getQuantidade());
		hamburguerEntity.setValor(hamburguerDTO.getValor());
		hamburguerEntity.setDescricao(hamburguerDTO.getDescricao());
	}

	private static HamburguerEntity toEntity(final HamburguerDTO hamburguerDTO) {
		final String nome = hamburguerDTO.getNome();
		final Double quantidade = hamburguerDTO.getQuantidade();
		final Double valor = hamburguerDTO.getValor();
		final String descricao = hamburguerDTO.getDescricao();
		return new HamburguerEntity(nome, quantidade, valor, descricao);
	}

	private static HamburguerDTO toDTO(final HamburguerEntity hamburguerEntity) {
		final long id = hamburguerEntity.getId();
		final String nome = hamburguerEntity.getNome();
		final Double quantidade = hamburguerEntity.getQuantidade();
		final Double valor = hamburguerEntity.getValor();
		final String descricao = hamburguerEntity.getDescricao();
		return new HamburguerDTO(id, nome, quantidade, valor, descricao);
	}

	List<HamburguerDTO> getAllFoods() {
		final List<HamburguerDTO> comidas = new ArrayList<>();
		this.hamburguerRepository.findAll().forEach(hamburguerEntity -> comidas.add(HamburguerController.toDTO(hamburguerEntity)));
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

	HamburguerDTO getFood(final Long id) {
		final Optional<HamburguerEntity> optionalHamburguer = this.hamburguerRepository.findById(id);
		if (optionalHamburguer.isPresent()) {
			return HamburguerController.toDTO(optionalHamburguer.get());
		}
		return HamburguerDTO.NULL_VALUE;
	}

	HamburguerDTO removeFood(final Long id) {
		final Optional<HamburguerEntity> optionalHamburguer = this.hamburguerRepository.findById(id);
		if (optionalHamburguer.isPresent()) {
			final HamburguerEntity hamburguerEntity = optionalHamburguer.get();
			this.hamburguerRepository.delete(hamburguerEntity);
			return HamburguerController.toDTO(hamburguerEntity);
		}
		return HamburguerDTO.NULL_VALUE;
	}

	Long inserirFood(final HamburguerDTO hamburguerDTO) {
		final HamburguerEntity hamburguerEntity = HamburguerController.toEntity(hamburguerDTO);
		this.hamburguerRepository.save(hamburguerEntity);
		return hamburguerEntity.getId();
	}

	HamburguerDTO atualizarHamburguer(final Long id, final HamburguerDTO hamburguerDTO) {
		final Optional<HamburguerEntity> optionalHamburguer = this.hamburguerRepository.findById(id);
		if (optionalHamburguer.isPresent()) {
			final HamburguerEntity hamburguerEntity = optionalHamburguer.get();
			final HamburguerDTO oldHamburguerDTO = HamburguerController.toDTO(hamburguerEntity);
			HamburguerController.atualizarEntityFromDTO(hamburguerDTO, hamburguerEntity);
			this.hamburguerRepository.save(hamburguerEntity);
			return oldHamburguerDTO;
		}
		return HamburguerDTO.NULL_VALUE;
	}
}