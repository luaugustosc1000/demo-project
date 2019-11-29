package br.edu.sc.senai.demo.demoproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
final class LoginController {

	private final LoginRepository loginRepository;

	LoginController(final LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	private static void atualizarEntityFromDTO(final LoginDTO loginDTO, final LoginEntity loginEntity) {
		loginEntity.setLogin(loginDTO.getLogin());
		loginEntity.setSenha(loginDTO.getSenha());
	}

	private static LoginEntity toEntity(final LoginDTO loginDTO) {
		final String login = loginDTO.getLogin();
		final String senha = loginDTO.getSenha();
		return new LoginEntity(login, senha);
	}

	private static LoginDTO toDTO(final LoginEntity loginEntity) {
		final String login = loginEntity.getLogin();
		final String senha = loginEntity.getSenha();
		return new LoginDTO(login, senha);
	}

	List<LoginDTO> getAllLogins() {
		final List<LoginDTO> logins = new ArrayList<>();
		this.loginRepository.findAll().forEach(loginEntity -> logins.add(LoginController.toDTO(loginEntity)));
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
		return logins;
	}

	LoginDTO getLogin(final Long id) {
		final Optional<LoginEntity> optionalLogin = this.loginRepository.findById(id);
		if (optionalLogin.isPresent()) {
			return LoginController.toDTO(optionalLogin.get());
		}
		return LoginDTO.NULL_VALUE;
	}

	LoginDTO removeLogin(final Long id) {
		final Optional<LoginEntity> optionalLogin = this.loginRepository.findById(id);
		if (optionalLogin.isPresent()) {
			final LoginEntity loginEntity = optionalLogin.get();
			this.loginRepository.delete(loginEntity);
			return LoginController.toDTO(loginEntity);
		}
		return LoginDTO.NULL_VALUE;
	}

	Long inserirLogin(final LoginDTO loginDTO) {
		final LoginEntity loginEntity = LoginController.toEntity(loginDTO);
		this.loginRepository.save(loginEntity);
		return loginEntity.getId();
	}

	LoginDTO atualizarLogin(final Long id, final LoginDTO loginDTO) {
		final Optional<LoginEntity> optionalLogin = this.loginRepository.findById(id);
		if (optionalLogin.isPresent()) {
			final LoginEntity loginEntity = optionalLogin.get();
			final LoginDTO oldLoginDTO = LoginController.toDTO(loginEntity);
			LoginController.atualizarEntityFromDTO(loginDTO, loginEntity);
			return oldLoginDTO;
		}
		return LoginDTO.NULL_VALUE;
	}
}