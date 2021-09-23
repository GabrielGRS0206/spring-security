package br.com.github.authentice.domain.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.github.authentice.api.mapper.dto.UserRequestDTO;
import br.com.github.authentice.domain.exception.userException.LoginInvalidoException;
import br.com.github.authentice.domain.exception.userException.TokenExpiradoException;
import br.com.github.authentice.domain.exception.userException.TokenInvalidoException;
import br.com.github.authentice.domain.exception.userException.UserBlockedException;
import br.com.github.authentice.domain.model.TokenJwt;
import br.com.github.authentice.domain.model.UserSystem;
import br.com.github.authentice.domain.utils.CryptUtil;
import io.jsonwebtoken.Claims;

@Service
public class AuthenticeService {

	private static final String BEARER = "Bearer";

	private static final Integer ONE_TRY = 1;

	private static final Integer MAX_ERROR = 3;

	private static final String IMCOMPLET = "Dados incompletos";

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserService userService;

	public TokenJwt authentice(UserRequestDTO request) {

		firstValidation(request);

		var token = new TokenJwt();
		token.setType(BEARER);

		UserSystem user = userService.findByEmail(request.getEmail());

		boolean passwordOk = CryptUtil.pswOk(request.getPassword(), user.getPassword());

		if (user.isBlocked()) {
			throw new UserBlockedException();
		}

		if (passwordOk) {
			token.setToken(tokenService.generateToken(user.getUsername()));
		} else {
			user.setPasswordError(user.getPasswordError() + ONE_TRY);

			if (maxErrorPassword(user)) {
				blockedUser(user);
			}
			throw new LoginInvalidoException();
		}
		return token;
	}

	private void blockedUser(UserSystem user) {
		user.setBlocked("S");
		userService.update(user);
	}

	private void firstValidation(UserRequestDTO request) {
		if (request.getEmail().equals("") || !request.getEmail().contains("@") || request.getPassword().equals("")) {
			throw new LoginInvalidoException(IMCOMPLET);
		}
	}

	private boolean maxErrorPassword(UserSystem user) {
		return user.getPasswordError().equals(MAX_ERROR);
	}

	public boolean validaToken(String token) {
		try {
			String tokenAuthorization = token.replace("Bearer ", "");
			Claims claims = tokenService.decodeToken(tokenAuthorization);

			// Verifica se o token está expirado
			if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
				throw new TokenExpiradoException();
			}
			return true;
		} catch (TokenExpiradoException et) {
			throw new TokenExpiradoException();
		} catch (Exception e) {
			throw new TokenInvalidoException();
		}
	}
}
