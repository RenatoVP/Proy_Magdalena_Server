package com.magdalena.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magdalena.entity.Rol;
import com.magdalena.repository.RolRepository;

@Service
public class RolServiceImpl implements RolService {
 
	@Autowired
	private RolRepository repositorio;
	@Override
	public List<Rol> ListarTodoLosPerfiles() {
		return repositorio.findAll();
	}
	
	public Optional<Rol> findByNombre(String nombre) {
		return repositorio.findByNombre(nombre);
	}
	
	@Override
	public Rol guardarPerfil(Rol perfil) {
		return repositorio.save(perfil);
	}
	@Override
	public Rol obtenerPerfilId(int id) {
		return repositorio.findById( id).get();
	}
	@Override
	public Rol actualizarPerfil(Rol perfil) {
		return repositorio.save(perfil);
	}
	@Override
	public void eliminarPerfil(int id) {
		repositorio.deleteById(id);
		
	}
}
