package br.com.minhasfinancas.service;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.minhasfinancas.domain.Usuario;
import br.com.minhasfinancas.repositories.UsuarioRepository;
import br.com.minhasfinancas.services.UsuarioService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UsuarioServiceTest {

	@Autowired
	private UsuarioService service;

	@Autowired
	private UsuarioRepository repository;

	@Test
	@Order(1)
	public void deveValidarEmail() {
		// Cenario
		repository.deleteAll();

		// Ação
		service.validarEmail("usuario@hotmail.com");

	}

	@Test
	@Order(2)
	public void deveLancarErroAoValidarEmailCadastrado() {
		// Cenario
		Usuario usuario = new Usuario(null, "Usuario", "usuario@hotmail.com", "123456");
		repository.save(usuario);

		// Ação
//		service.validarEmail("usuario@hotmail.com");

	}
}
