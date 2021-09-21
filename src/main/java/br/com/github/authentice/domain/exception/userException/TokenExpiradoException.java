package br.com.github.authentice.domain.exception.userException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.github.authentice.domain.exception.negocio.NegocioException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TokenExpiradoException extends NegocioException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MSG_TOKEN_EXPIRADO = "Token expirado";
	
	public TokenExpiradoException(String message) {
		super(message);
	}
	
	public TokenExpiradoException() {
		super(MSG_TOKEN_EXPIRADO);
	}
}
