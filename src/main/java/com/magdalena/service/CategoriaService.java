package com.magdalena.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magdalena.entity.Categoria;
import com.magdalena.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
	public Categoria findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Categoria save(Categoria bean) {
		return repository.save(bean);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
