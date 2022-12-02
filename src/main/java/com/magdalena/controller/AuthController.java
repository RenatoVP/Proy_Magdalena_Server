package com.magdalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magdalena.config.JwtTokenProvider;
import com.magdalena.utils.JWTAuthResponseDTO;
import com.magdalena.utils.LoginDTO;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/ingresar")
	public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO logindto){
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(logindto.getUsername(), logindto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		//Obtenemos el token del jwtTokenProvider
		String token = jwtTokenProvider.generateToken(auth);
		return ResponseEntity.ok(new JWTAuthResponseDTO(token));
	}
}
