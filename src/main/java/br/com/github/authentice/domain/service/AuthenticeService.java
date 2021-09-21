package br.com.github.authentice.domain.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.github.authentice.api.mapper.dto.UserRequestDTO;
import br.com.github.authentice.domain.exception.userException.LoginInvalidoException;
import br.com.github.authentice.domain.exception.userException.TokenExpiradoException;
import br.com.github.authentice.domain.exception.userException.TokenInvalidoException;
import br.com.github.authentice.domain.exception.userException.UserBlockedException;
import br.com.github.authentice.domain.model.TokenJwt;
import br.com.github.authentice.domain.model.User;
import io.jsonwebtoken.Claims;

@Service
public class AuthenticeService {

	private static final String BEARER = "Bearer";

	private static final Integer ONE_TRY = 1;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;


	public TokenJwt authentice(UserRequestDTO request) {

		System.out.println("DADOS LOGIN : " + request.getEmail() + " | " + request.getPassword());

		var token = new TokenJwt();
		token.setType(BEARER);

		User user = userService.findByUserEmail(request.getEmail().trim());
		
		try {
			if (user != null && !user.isBlocked()) {
				if (authorize(getHashMd5(request.getPassword()),user.getPassword())) {
					token.setToken(tokenService.generateToken(user));
				} else {
					user.setPasswordError(user.getPasswordError()+ONE_TRY);

					if(user.getPasswordError().equals(3)) {
						user.setBlocked("S");
					}
					
					userService.update(user);
					
					throw new LoginInvalidoException();
				}
			} else if(user.isBlocked()) {
				throw new UserBlockedException();
			}
		} catch (Exception e) {
		}

		return token;
	}

	private boolean authorize(String request, String passwordBd) {
		return request.equals(passwordBd);
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
	
	public static String getHashMd5(String value) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        BigInteger hash = new BigInteger(1, md.digest(value.trim().getBytes()));
        return hash.toString(16);
    }

}
