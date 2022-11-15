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
	public List<Perfil> listarTodosLosPerfiles() {
		return repositorio.findAll();
	}
}
