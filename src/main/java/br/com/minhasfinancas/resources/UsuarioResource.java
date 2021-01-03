package br.com.minhasfinancas.resources;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.minhasfinancas.domain.Usuario;
import br.com.minhasfinancas.exception.ErroDeAtutenticacao;
import br.com.minhasfinancas.exception.RegraNegocioException;
import br.com.minhasfinancas.services.UsuarioService;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioResource {

	private UsuarioService service;

	public UsuarioResource(UsuarioService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Serializable> salvar(@RequestBody Usuario usuario) {
		Usuario usuarioSalvo = service.salvarUsuario(usuario);
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
		} catch (ErroDeAtutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}
	
	@PostMapping("/autenticar")
	public ResponseEntity<Serializable> autenticar(@RequestBody Usuario usuario) {
		try {
			Usuario usuarioAutenticado = service.autenticar(usuario.getEmail(), usuario.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}

}
