package br.com.minhasfinancas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.minhasfinancas.domain.Lancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {

}
