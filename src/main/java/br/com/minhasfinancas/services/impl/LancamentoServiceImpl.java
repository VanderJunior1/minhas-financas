package br.com.minhasfinancas.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.minhasfinancas.domain.Lancamento;
import br.com.minhasfinancas.domain.enums.StatusLancamento;
import br.com.minhasfinancas.exception.RegraNegocioException;
import br.com.minhasfinancas.repositories.LancamentoRepository;
import br.com.minhasfinancas.services.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService{
	
	private LancamentoRepository repository;
	
	public LancamentoServiceImpl(LancamentoRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		lancamento.setId(null);
		validar(lancamento);
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		validar(lancamento);
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());	
		repository.delete(lancamento);
		
	}

	@Override
	@Transactional(readOnly = true	)
	public List<Lancamento> buscar(Lancamento lancamento) {
		Example example = Example.of(lancamento, ExampleMatcher.matching()
									.withIgnoreCase().
									withStringMatcher(StringMatcher.CONTAINING));
		return repository.findAll(example);
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento statusLancamento) {
		lancamento.setStatus(statusLancamento);
		atualizar(lancamento);
		
	}

	@Override
	public void validar(Lancamento lancamento) {
		if(lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Informe uma Descrição válida");
		}
		if(lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
			throw new RegraNegocioException("Informe um Mês válido");
		}
		if(lancamento.getAno() == null || lancamento.getAno().toString().length() !=4) {
			throw new RegraNegocioException("Informe Ano válido");
		}
		if(lancamento.getUsuario() == null || lancamento.getUsuario() == null) {
			throw new RegraNegocioException("Informe um Usuário válido");
		}
		if(lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
			throw new RegraNegocioException("Informe um Valor válido");
		}
		if(lancamento.getTipo() == null) {
			throw new RegraNegocioException("Informe um Tipo de Lancamento válido");
		}
	}

}
