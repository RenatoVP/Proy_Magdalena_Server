package com.magdalena.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magdalena.entidad.Perfil;
import com.magdalena.repositorio.PerfilRepositorio;

@Service
public class PerfilServicioImpl implements PerfilServicio {
 
	@Autowired
	private PerfilRepositorio repositorio;
	@Override
	public List<Perfil> ListarTodoLosPerfiles() {
		return repositorio.findAll();
	}
	@Override
	public Perfil guardarPerfil(Perfil perfil) {
		return repositorio.save(perfil);
	}
	@Override
	public Perfil obtenerPerfilId(int id) {
		return repositorio.findById( id).get();
	}
	@Override
	public Perfil actualizarPerfil(Perfil perfil) {
		return repositorio.save(perfil);
	}
	@Override
	public void eliminarPerfil(int id) {
		repositorio.deleteById(id);
		
	}
}
