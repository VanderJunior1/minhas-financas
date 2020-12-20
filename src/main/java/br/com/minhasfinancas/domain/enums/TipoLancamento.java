package br.com.minhasfinancas.domain.enums;

public enum TipoLancamento {

	RECEITA(1, "Receita"), 
	DESPESA(2, "Despesa");

	private int cod;
	private String descricao;

	private TipoLancamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoLancamento toEnum(Integer cod) {
		if (cod.equals(null)) {
			return null;
		}

		for (TipoLancamento x : TipoLancamento.values()) {
			return x;
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
