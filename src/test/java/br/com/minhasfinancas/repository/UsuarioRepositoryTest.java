package br.com.minhasfinancas.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.minhasfinancas.domain.Usuario;
import br.com.minhasfinancas.repositories.UsuarioRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;

	@Test
	@Order(1)
	public void deveVerificarExistenciaEmail() {
		// Cenario
		Usuario usuario = new Usuario(null, "Usuario", "usuario@hotmail.com", "123456");
		repository.save(usuario);
		
		assertThat(usuario.getId()).isNotNull();

		// Ação
		boolean result = repository.existsByEmail("usuario@hotmail.com");

		// Verificação
		assertThat(result).isTrue();
		
		
		Optional<Usuario> user = repository.findByEmail("usuario@hotmail.com");
		assertThat(user.isPresent());
		
		Optional<Usuario> user2 = repository.findByEmailAndNome("usuario@hotmail.com", "Usuario");
		assertThat(user2.isPresent());
	}

	@Test
	@Order(2)
	public void deveRetornarFalsoQuandoNaoHouverUsuarioComEmail() {
		// Cenario
		repository.deleteAll();

		// Ação
		boolean result = repository.existsByEmail("usuario@hotmail.com");

		// Verificação
		assertThat(result).isFalse();
	}

}
