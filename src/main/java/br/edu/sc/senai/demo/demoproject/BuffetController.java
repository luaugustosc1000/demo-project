	package br.edu.sc.senai.demo.demoproject;

	import java.util.ArrayList;
	import java.util.List;
	import java.util.Optional;
	import org.springframework.stereotype.Controller;

	@Controller
	final class BuffetController {

		private final BuffetRepository buffetRepository;

		BuffetController(final BuffetRepository buffetRepository) {
			this.buffetRepository = buffetRepository;
		}

		private static void atualizarEntityFromDTO(final BuffetDTO buffetDTO, final BuffetEntity buffetEntity) {
			buffetEntity.setNome(buffetDTO.getNome());			
		}

		private static BuffetEntity toEntity(final BuffetDTO buffetDTO) {
			final String nome = buffetDTO.getNome();
			return new BuffetEntity(nome);
		}

		private static BuffetDTO toDTO(final BuffetEntity buffetEntity) {
			final String nome = buffetEntity.getNome();	
			return new BuffetDTO(nome);
		}

		List<BuffetDTO> getAllFoods() {
			final List<BuffetDTO> comidas = new ArrayList<>();
			this.buffetRepository.findAll().forEach(buffetEntity -> comidas.add(BuffetController.toDTO(buffetEntity)));
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

		BuffetDTO getFood(final Long id) {
			final Optional<BuffetEntity> optionalBuffet = this.buffetRepository.findById(id);
			if (optionalBuffet.isPresent()) {
				return BuffetController.toDTO(optionalBuffet.get());
			}
			return BuffetDTO.NULL_VALUE;
		}

		BuffetDTO removeFood(final Long id) {
			final Optional<BuffetEntity> optionalBuffet = this.buffetRepository.findById(id);
			if (optionalBuffet.isPresent()) {
				final BuffetEntity buffetEntity = optionalBuffet.get();
				this.buffetRepository.delete(buffetEntity);
				return BuffetController.toDTO(buffetEntity);
			}
			return BuffetDTO.NULL_VALUE;
		}

		Long inserirFood(final BuffetDTO buffetDTO) {
			final BuffetEntity buffetEntity = BuffetController.toEntity(buffetDTO);
			this.buffetRepository.save(buffetEntity);
			return buffetEntity.getId();
		}

		BuffetDTO atualizarBuffet(final Long id, final BuffetDTO buffetDTO) {
			final Optional<BuffetEntity> optionalBuffet = this.buffetRepository.findById(id);
			if (optionalBuffet.isPresent()) {
				final BuffetEntity buffetEntity = optionalBuffet.get();
				final BuffetDTO oldBuffetDTO = BuffetController.toDTO(buffetEntity);
				BuffetController.atualizarEntityFromDTO(buffetDTO, buffetEntity);
				this.buffetRepository.save(buffetEntity);
				return oldBuffetDTO;
			}
			return BuffetDTO.NULL_VALUE;
		}
	}