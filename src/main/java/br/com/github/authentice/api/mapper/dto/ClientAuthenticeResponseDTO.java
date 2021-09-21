package br.com.github.authentice.api.mapper.dto;

import br.com.github.authentice.domain.model.TokenJwt;

public class ClientAuthenticeResponseDTO {
	private String token;
	private String type;

	public ClientAuthenticeResponseDTO(TokenJwt user) {
		this.token = user.getToken();
		this.type = user.getType();
	}

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
