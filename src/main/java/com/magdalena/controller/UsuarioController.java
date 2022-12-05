package com.magdalena.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magdalena.entity.Producto;
import com.magdalena.entity.Rol;
import com.magdalena.entity.Usuario;
import com.magdalena.service.RolServiceImpl;
import com.magdalena.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RolServiceImpl rolservice;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	public ResponseEntity<?> registrar(@RequestBody Usuario usuario){
		if(usuarioService.existsByUsername(usuario.getUsername())) {
			return new ResponseEntity<>("El nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
		}
		
		Usuario bean = new Usuario();
		bean.setNombre(usuario.getNombre());
		bean.setApellidos(usuario.getApellidos());
		bean.setUsername(usuario.getUsername());
		bean.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		Rol roles = rolservice.findByNombre("ROLE_ADMIN").get();
		bean.setRoles(Collections.singleton(roles));
		
		usuarioService.save(bean);
		return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> get(@PathVariable Long id){
		Usuario bean = usuarioService.findById(id);
		
		if(bean == null) {
			return new ResponseEntity<Usuario>(bean, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Usuario>(bean, HttpStatus.OK);
	}
}
