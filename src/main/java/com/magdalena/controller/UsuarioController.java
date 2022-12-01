package com.magdalena.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@GetMapping("/login")
	public String loginForm() {
		return "Usuario/formLogin";
	}
}
