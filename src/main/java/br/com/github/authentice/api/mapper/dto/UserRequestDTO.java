package br.com.github.authentice.api.mapper.dto;

public class UserRequestDTO {

	private String email;
	private String password;

	public UserRequestDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email.trim();
	}

	public String getPassword() {
		return password.trim();
	}

}
