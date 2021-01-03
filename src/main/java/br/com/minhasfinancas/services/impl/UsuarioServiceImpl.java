package br.com.minhasfinancas.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.minhasfinancas.domain.Usuario;
import br.com.minhasfinancas.exception.ErroDeAtutenticacao;
import br.com.minhasfinancas.exception.RegraNegocioException;
import br.com.minhasfinancas.repositories.UsuarioRepository;
import br.com.minhasfinancas.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		if (!usuario.isPresent()) {
			throw new ErroDeAtutenticacao("Usuário informado não foi encontrado");
		}

		if (!usuario.get().getSenha().equals(senha)) {
			throw new ErroDeAtutenticacao("Senha informada inválida.");
		}

		return usuario.get();

	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {

		boolean existe = repository.existsByEmail(email);
		if (existe) {
			throw new RegraNegocioException("Já existe um usuario cadastrado com o email informado: " + email);
		}

	}

}
