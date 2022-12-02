package com.magdalena.utils;

public class JWTAuthResponseDTO {
	private String tokenAcces;
	private String tokenType;
	
	public JWTAuthResponseDTO(String tokenAcces) {
		this.tokenAcces = tokenAcces;
	}
	
	public JWTAuthResponseDTO(String tokenAcces, String tokenType) {
		this.tokenAcces = tokenAcces;
		this.tokenType = tokenType;
	}

	public String getTokenAcces() {
		return tokenAcces;
	}

	public void setTokenAcces(String tokenAcces) {
		this.tokenAcces = tokenAcces;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
}
