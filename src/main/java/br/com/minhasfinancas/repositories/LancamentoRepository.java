package br.com.minhasfinancas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.minhasfinancas.domain.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {

}
