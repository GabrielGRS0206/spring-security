package br.com.github.authentice.domain.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.github.authentice.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    public static  String key = "SECRETS";
    private static final long EXPIRATION_TIME = 36000000;
    static final String SECRET = "MySecret";
 	static final String TOKEN_PREFIX = "Bearer";
 	static final String HEADER_STRING = "Authorization";

   

    public String generateToken(User usuario) {
    	
    	String JWT = Jwts.builder()
				.setSubject(usuario.getUsername())
//				.setSubject("ROLE_USER")
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
    	
       /* return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject("Teste JWT API")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();*/
    	
    	return JWT;
    }

    public Claims decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getTokenId(String token) {
		Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		return body.getSubject();
	}
}