package br.com.github.authentice.domain.exception.userException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.github.authentice.domain.exception.negocio.NegocioException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserBlockedException extends NegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String USER_BLOCK = "Usuário bloqueado";

	public UserBlockedException(String message) {
		super(message);
	}

	public UserBlockedException() {
		super(USER_BLOCK);
	}
}
