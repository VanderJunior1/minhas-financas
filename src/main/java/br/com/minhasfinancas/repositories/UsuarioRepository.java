package br.com.minhasfinancas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.minhasfinancas.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	//Pela convençao queryMethods "findBy" + propriedade da classe ele automaticamente busca na entidade.
	Optional<Usuario> findByEmail(String email);
	
	//Outra convenção "existsBy"
	boolean existsByEmail(String email);
	
	//Pode ser quantas propriedades tiverem a classe da entidade.
	Optional<Usuario> findByEmailAndNome(String email, String nome);

}
