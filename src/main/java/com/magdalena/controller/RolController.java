package com.magdalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.magdalena.entity.Rol;
import com.magdalena.service.PerfilService;

@Controller
public class RolController {

	@Autowired
	private PerfilService servicio;  

	@GetMapping({ "/perfil", "/" })
	public String listarPerfiles(Model modelo) {
		modelo.addAttribute("perfil", servicio.ListarTodoLosPerfiles());
		return "perfil";
	}

	@GetMapping("/perfil/nuevo")
	public String mostrarFormularioDeRegistrtarPerfil(Model modelo) {
		Rol perfil = new Rol();
		modelo.addAttribute("perfil", perfil);
		return "crearperfil";
	}

	@PostMapping("/perfil")
	public String guardarPerfil(@ModelAttribute("perfil") Rol perfil) {
		servicio.guardarPerfil(perfil);
		return "redirect:/perfil";
	}

	@GetMapping("/perfil/editar/{id}")
	public String mostrarFormularioDeEditar(@PathVariable int id, Model modelo) {
		modelo.addAttribute("perfil", servicio.obtenerPerfilId(id));
		return "editarperfil";
	}

	@PostMapping("/perfil/{id}")
	public String actualizarPerfil(@PathVariable int id, @ModelAttribute("perfil") Rol perfil,
			Model modelo) {
		Rol perfilExistente = servicio.obtenerPerfilId(id);
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
