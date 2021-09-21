package br.com.github.authentice.domain.exception.userException;

import br.com.github.authentice.domain.exception.negocio.NegocioException;

public class DtoInvalidoException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public DtoInvalidoException(String mensagem) {
		super(mensagem);
	}

	public DtoInvalidoException(StringBuilder builder) {
		
		this(builder.toString());
	}

}
