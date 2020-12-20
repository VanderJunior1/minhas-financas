package br.com.minhasfinancas.domain.enums;

public enum StatusLancamento {

	PENDENTE(1, "Pendente"), 
	CANCELADO(2, "Cancelado"), 
	EFETIVADO(3, "Efetivado");

	private int cod;
	private String descricao;

	private StatusLancamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusLancamento toEnum(Integer cod) {
		if (cod.equals(null)) {
			return null;
		}

		for (StatusLancamento x : StatusLancamento.values()) {
			return x;
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
