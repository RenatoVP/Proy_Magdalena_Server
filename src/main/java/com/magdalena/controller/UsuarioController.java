package com.magdalena.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magdalena.entity.Producto;
import com.magdalena.entity.Rol;
import com.magdalena.entity.Usuario;
import com.magdalena.excepciones.ResourceNotFoundException;
import com.magdalena.service.RolServiceImpl;
import com.magdalena.service.UsuarioService;
import com.magdalena.repository.UsuarioRepositorio;

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
	
	@Autowired
	private UsuarioRepositorio repositorio;
	
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
	
	/*@GetMapping("/{id}")
	public ResponseEntity<Usuario> get(@PathVariable Long id){
		Usuario bean = usuarioService.findById(id);
		
		if(bean == null) {
			return new ResponseEntity<Usuario>(bean, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Usuario>(bean, HttpStatus.OK);
	}*/
	
	
	
	//este metodo sirve para listar todos los usuarios
	@GetMapping()
	public List<Usuario> listarTodosLosUsuarios() {
		return repositorio.findAll();
	}
//este metodo sirve para guardar el usuarios
	/*@PostMapping("/guardar")
	public Usuario guardarUsuario(@RequestBody Usuario usuario) {
		return repositorio.save(usuario);
	}*/
	
//este metodo sirve para buscar un usuario
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id){
		Usuario usuario = repositorio.findById(id)
					            .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));
			return ResponseEntity.ok(usuario);
	}			
//este metodo sirve para actualizar usuario
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id,@RequestBody Usuario detallesUsuario){
		Usuario usuario = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));
		
		usuario.setNombre(detallesUsuario.getNombre());
		usuario.setApellidos(detallesUsuario.getApellidos());
		usuario.setUsername(detallesUsuario.getUsername());
		usuario.setPassword(detallesUsuario.getPassword());
		
		Usuario usuarioActualizado = repositorio.save(usuario);
		return ResponseEntity.ok(usuarioActualizado);
    }
	
	//este metodo sirve para eliminar un usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarUsuario(@PathVariable Long id){
		Usuario usuario = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));
		
		repositorio.delete(usuario);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }	
}
