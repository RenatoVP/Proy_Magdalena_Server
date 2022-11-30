package com.magdalena.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magdalena.entity.Categoria;
import com.magdalena.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin("*")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		List<Categoria> lista = categoriaService.findAll();
		return new ResponseEntity<List<Categoria>>(lista, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> registrar(@RequestBody Categoria bean) {
		Categoria categoria = categoriaService.save(bean);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> editar(@PathVariable Long id, @RequestBody Categoria bean) {
		Categoria categoria = categoriaService.findById(id);
		
		if(categoria == null) return new ResponseEntity<Categoria>(categoria, HttpStatus.INTERNAL_SERVER_ERROR);
		
		bean.setId(categoria.getId());
		
		categoriaService.save(bean);
		return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		Categoria categoria = categoriaService.findById(id);
		
		if(categoria == null) return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		categoriaService.delete(id);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
