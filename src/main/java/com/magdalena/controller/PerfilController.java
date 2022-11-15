package com.magdalena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.magdalena.entidad.Perfil;

@Controller
public class PerfilController {
	//Registrar
		@GetMapping("/perfil/cargar")
		public String cargarformRegistro(Model model) {
			model.addAttribute("perfil", new Perfil());
			return "RegistroPerfil";
		}
}
