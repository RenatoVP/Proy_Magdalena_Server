package com.magdalena.servicio;

import java.util.List;

import com.magdalena.entidad.Perfil;

public interface PerfilServicio {
	public List<Perfil> ListarTodoLosPerfiles();

	public Perfil guardarPerfil(Perfil perfil);

	public Perfil obtenerPerfilId(int id);

	public Perfil actualizarPerfil(Perfil perfil);

	public void eliminarPerfil(int id);
}
