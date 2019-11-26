package br.pucminas.api.security.dto;

public class TokenDto {

	private String token;
	private String user;

	public TokenDto() {
	}

	public TokenDto(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUser() {
		return user;
	}

	public TokenDto withUser(String user) {
		this.user = user;
		return this;
	}

}
