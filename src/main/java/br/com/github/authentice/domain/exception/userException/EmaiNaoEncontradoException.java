package br.com.github.authentice.domain.exception.userException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.github.authentice.domain.exception.negocio.NegocioException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmaiNaoEncontradoException extends NegocioException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MSG_EMAIL_NAO_ENCONTRADO = "Email não encontrado";

	public EmaiNaoEncontradoException(String message) {
		super(message);
	}

	public EmaiNaoEncontradoException() {
		super(MSG_EMAIL_NAO_ENCONTRADO);
	}
}
