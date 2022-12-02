package com.magdalena.service;

import java.util.List;

import com.magdalena.entity.Rol;

public interface RolService {
	public List<Rol> ListarTodoLosPerfiles();

	public Rol guardarPerfil(Rol perfil);

	public Rol obtenerPerfilId(int id);

	public Rol actualizarPerfil(Rol perfil);

	public void eliminarPerfil(int id);
}
