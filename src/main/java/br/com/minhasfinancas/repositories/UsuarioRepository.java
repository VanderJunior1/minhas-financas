package br.com.minhasfinancas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.minhasfinancas.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
