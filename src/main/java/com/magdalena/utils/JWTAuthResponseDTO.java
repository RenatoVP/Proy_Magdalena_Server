package com.magdalena.utils;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthResponseDTO {
	private String tokenAcces;
	private String tokenType = "Bearer";
	private Long id;
	private String username;
	private List<String> roles;
	
	public JWTAuthResponseDTO(String tokenAcces, Long id, String username, List<String> roles) {
		this.tokenAcces = tokenAcces;
		this.id = id;
		this.username = username;
		this.roles = roles;
	}
	
}
