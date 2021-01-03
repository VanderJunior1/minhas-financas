package br.com.minhasfinancas.exception;

public class ErroDeAtutenticacao extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ErroDeAtutenticacao(String msg) {
		super(msg);
	}
}
