package com.magdalena.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magdalena.config.JwtTokenProvider;
import com.magdalena.entity.Usuario;
import com.magdalena.service.UsuarioService;
import com.magdalena.utils.JWTAuthResponseDTO;
import com.magdalena.utils.LoginDTO;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/ingresar")
	public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO logindto){
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(logindto.getUsername(), logindto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		//Obtenemos el token del jwtTokenProvider
		String token = jwtTokenProvider.generateToken(auth);
		//return ResponseEntity.ok(new JWTAuthResponseDTO(token));
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		Usuario usuario = usuarioService.findByUsername(userDetails.getUsername()).get();
		
		return ResponseEntity.ok(new JWTAuthResponseDTO(token, usuario.getId(), usuario.getUsername(), roles));
	}
}
