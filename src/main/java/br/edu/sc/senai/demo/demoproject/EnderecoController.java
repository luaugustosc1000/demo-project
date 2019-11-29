package br.edu.sc.senai.demo.demoproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
final class EnderecoController {

	private final EnderecoRepository enderecoRepository;

	EnderecoController(final EnderecoRepository enderecoRepository) {
		this.enderecoRepository = enderecoRepository;
	}

	private static void atualizarEntityFromDTO(final EnderecoDTO enderecoDTO, final EnderecoEntity enderecoEntity) {
		enderecoEntity.setEndereco(enderecoDTO.getEndereco());
		enderecoEntity.setBairro(enderecoDTO.getBairro());
		enderecoEntity.setCidade(enderecoDTO.getCidade());
		enderecoEntity.setEstado(enderecoDTO.getEstado());
	}

	private static EnderecoEntity toEntity(final EnderecoDTO enderecoDTO) {
		final String endereco = enderecoDTO.getEndereco();
		final String bairro = enderecoDTO.getBairro();
		final String cidade = enderecoDTO.getCidade();
		final String estado = enderecoDTO.getEstado();
		return new EnderecoEntity(endereco, bairro, cidade, estado);
	}

	private static EnderecoDTO toDTO(final EnderecoEntity enderecoEntity) {
		final String endereco = enderecoEntity.getEndereco();
		final String bairro = enderecoEntity.getBairro();
		final String cidade = enderecoEntity.getCidade();
		final String estado = enderecoEntity.getEstado();
		return new EnderecoDTO(endereco, bairro, cidade, estado);
	}

	List<EnderecoDTO> getAllAdresses() {
		final List<EnderecoDTO> enderecos = new ArrayList<>();
		this.enderecoRepository.findAll()
				.forEach(enderecoEntity -> enderecos.add(EnderecoController.toDTO(enderecoEntity)));
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
		return enderecos;
	}

	EnderecoDTO getAdress(final Long id) {
		final Optional<EnderecoEntity> optionalEndereco = this.enderecoRepository.findById(id);
		if (optionalEndereco.isPresent()) {
			return EnderecoController.toDTO(optionalEndereco.get());
		}
		return EnderecoDTO.NULL_VALUE;
	}

	EnderecoDTO removeAdress(final Long id) {
		final Optional<EnderecoEntity> optionalEndereco = this.enderecoRepository.findById(id);
		if (optionalEndereco.isPresent()) {
			final EnderecoEntity enderecoEntity = optionalEndereco.get();
			this.enderecoRepository.delete(enderecoEntity);
			return EnderecoController.toDTO(enderecoEntity);
		}
		return EnderecoDTO.NULL_VALUE;
	}

	Long inserirAdress(final EnderecoDTO enderecoDTO) {
		final EnderecoEntity enderecoEntity = EnderecoController.toEntity(enderecoDTO);
		this.enderecoRepository.save(enderecoEntity);
		return enderecoEntity.getId();
	}

	EnderecoDTO atualizarAdress(final Long id, final EnderecoDTO enderecoDTO) {
		final Optional<EnderecoEntity> optionalEndereco = this.enderecoRepository.findById(id);
		if (optionalEndereco.isPresent()) {
			final EnderecoEntity enderecoEntity = optionalEndereco.get();
			final EnderecoDTO oldEnderecoDTO = EnderecoController.toDTO(enderecoEntity);
			EnderecoController.atualizarEntityFromDTO(enderecoDTO, enderecoEntity);
			this.enderecoRepository.save(enderecoEntity);
			return oldEnderecoDTO;
		}
		return EnderecoDTO.NULL_VALUE;
	}
}