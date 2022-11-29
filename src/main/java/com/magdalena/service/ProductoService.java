package com.magdalena.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magdalena.entity.Producto;
import com.magdalena.repository.ProductoRepository;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository repository;
	
	public List<Producto> findAll(){
		return repository.findAll();
	}
	
	public Producto findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<Producto> findByDescripcionContaining(String infix){
		return repository.findByDescripcionContaining(infix);
	}
	
	public Producto save(Producto bean) {
		return repository.save(bean);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
