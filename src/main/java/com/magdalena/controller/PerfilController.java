package com.magdalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.magdalena.entidad.Perfil;
import com.magdalena.servicio.PerfilServicio;

@Controller
public class PerfilController {

	@Autowired
	private PerfilServicio servicio;  

	@GetMapping({ "/perfil", "/" })
	public String listarPerfiles(Model modelo) {
		modelo.addAttribute("perfil", servicio.ListarTodoLosPerfiles());
		return "perfil";
	}

	@GetMapping("/perfil/nuevo")
	public String mostrarFormularioDeRegistrtarPerfil(Model modelo) {
		Perfil perfil = new Perfil();
		modelo.addAttribute("perfil", perfil);
		return "crearperfil";
	}

	@PostMapping("/perfil")
	public String guardarPerfil(@ModelAttribute("perfil") Perfil perfil) {
		servicio.guardarPerfil(perfil);
		return "redirect:/perfil";
	}

	@GetMapping("/perfil/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable int id, Model modelo) {
		modelo.addAttribute("perfil", servicio.obtenerPerfilId(id));
		return "editarperfil";
	}

	@PostMapping("/perfil/{id}")
	public String actualizarPerfil(@PathVariable int id, @ModelAttribute("perfil") Perfil perfil,
			Model modelo) {
		Perfil perfilExistente = servicio.obtenerPerfilId(id);
		perfilExistente.setId(id);
		perfilExistente.setNombre(perfil.getNombre());
		servicio.actualizarPerfil(perfilExistente);
		return "redirect:/perfil";
	}
	@GetMapping("/perfil/{id}")
	public String eliminarPerfil(@PathVariable int id) {
		servicio.eliminarPerfil(id);
		return "redirect:/perfil";
	}
}
