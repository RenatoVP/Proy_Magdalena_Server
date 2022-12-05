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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.magdalena.entity.Producto;
import com.magdalena.service.ProductoService;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin("http://localhost:4200")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listar() {
		List<Producto> lista = productoService.findAll();
		return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> listaProductoxNombre(@PathVariable Long id){
		Producto bean = productoService.findById(id);
		
		if(bean == null) {
			return new ResponseEntity<Producto>(bean, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Producto>(bean, HttpStatus.OK);
	}
	
	@GetMapping("/consulta")
	public ResponseEntity<List<Producto>> listaProductoxNombre(@RequestParam String descripcion){
		List<Producto> lista = productoService.findAllByDescripcionContaining(descripcion);
		return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Producto> registrar(@RequestBody Producto bean) {
		Producto producto = productoService.save(bean);
		return new ResponseEntity<Producto>(producto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Producto> editar(@PathVariable Long id, @RequestBody Producto bean) {
		Producto producto = productoService.findById(id);
		
		if(producto == null) return new ResponseEntity<Producto>(producto, HttpStatus.INTERNAL_SERVER_ERROR);
		
		bean.setId(producto.getId());
		
		productoService.save(bean);
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		
		if(producto == null) return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		productoService.delete(id);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
