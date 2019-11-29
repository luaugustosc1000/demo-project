package br.edu.sc.senai.demo.demoproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;

@Controller
final class CadastroPessoaController {

	private final CadastroPessoaRepository cadastroRepository;

	CadastroPessoaController(final CadastroPessoaRepository cadastroRepository) {
		this.cadastroRepository = cadastroRepository;
	}

	private static void atualizarEntityFromDTO(final CadastroPessoaDTO cadastroDTO, final CadastroPessoaEntity cadastroEntity) {
		cadastroEntity.setNome(cadastroDTO.getNome());
		cadastroEntity.setSobrenome(cadastroDTO.getSobrenome());
		cadastroEntity.setTelefone(cadastroDTO.getTelefone());
		cadastroEntity.setEmail(cadastroDTO.getEmail());
		cadastroEntity.setDataNascimento(cadastroDTO.getDataNascimento());
		cadastroEntity.setSenha(cadastroDTO.getSenha());
	}

	private static CadastroPessoaEntity toEntity(final CadastroPessoaDTO cadastroDTO) {
		final String nome = cadastroDTO.getNome();
		final String sobrenome = cadastroDTO.getSobrenome();
		final String telefone = cadastroDTO.getTelefone();
		final String email = cadastroDTO.getEmail();
		final String dataNascimento = cadastroDTO.getDataNascimento();
		final String senha = cadastroDTO.getSenha();
		return new CadastroPessoaEntity(nome, sobrenome, telefone, email, dataNascimento, senha);
	}

	private static CadastroPessoaDTO toDTO(final CadastroPessoaEntity cadastroEntity) {
		final String nome = cadastroEntity.getNome();
		final String sobrenome = cadastroEntity.getSobrenome();
		final String telefone = cadastroEntity.getTelefone();
		final String email = cadastroEntity.getEmail();
		final String dataNascimento = cadastroEntity.getDataNascimento();
		final String senha = cadastroEntity.getSenha();
		return new CadastroPessoaDTO(nome, sobrenome, telefone, email, dataNascimento, senha);
	}

	List<CadastroPessoaDTO> getAllRegisters() {
		final List<CadastroPessoaDTO> cadastros = new ArrayList<>();
		this.cadastroRepository.findAll()
				.forEach(cadastroEntity -> cadastros.add(CadastroPessoaController.toDTO(cadastroEntity)));
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
		return cadastros;
	}

	CadastroPessoaDTO getRegister(final Long id) {
		final Optional<CadastroPessoaEntity> optionalCadastro = this.cadastroRepository.findById(id);
		if (optionalCadastro.isPresent()) {
			return CadastroPessoaController.toDTO(optionalCadastro.get());
		}
		return CadastroPessoaDTO.NULL_VALUE;
	}

	CadastroPessoaDTO removeRegister(final Long id) {
		final Optional<CadastroPessoaEntity> optionalCadastro = this.cadastroRepository.findById(id);
		if (optionalCadastro.isPresent()) {
			final CadastroPessoaEntity cadastroEntity = optionalCadastro.get();
			this.cadastroRepository.delete(cadastroEntity);
			return CadastroPessoaController.toDTO(cadastroEntity);
		}
		return CadastroPessoaDTO.NULL_VALUE;
	}

	Long inserirRegister(final CadastroPessoaDTO cadastroDTO) {
		final CadastroPessoaEntity cadastroEntity = CadastroPessoaController.toEntity(cadastroDTO);
		this.cadastroRepository.save(cadastroEntity);
		return cadastroEntity.getId();
	}

	CadastroPessoaDTO atualizarRegister(final Long id, final CadastroPessoaDTO cadastroDTO) {
		final Optional<CadastroPessoaEntity> optionalCadastro = this.cadastroRepository.findById(id);
		if (optionalCadastro.isPresent()) {
			final CadastroPessoaEntity cadastroEntity = optionalCadastro.get();
			final CadastroPessoaDTO oldCadastroDTO = CadastroPessoaController.toDTO(cadastroEntity);
			CadastroPessoaController.atualizarEntityFromDTO(cadastroDTO, cadastroEntity);
			this.cadastroRepository.save(cadastroEntity);
			return oldCadastroDTO;
		}
		return CadastroPessoaDTO.NULL_VALUE;
	}
}