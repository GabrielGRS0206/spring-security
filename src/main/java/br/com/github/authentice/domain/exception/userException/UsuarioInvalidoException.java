package br.com.github.authentice.domain.exception.userException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.github.authentice.domain.exception.negocio.NegocioException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsuarioInvalidoException extends NegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MSG_USUARIO_INVALIDO = "Usuário inválido";

	public UsuarioInvalidoException(String message) {
		super(message);
	}

	public UsuarioInvalidoException() {
		super(MSG_USUARIO_INVALIDO);
	}
}
