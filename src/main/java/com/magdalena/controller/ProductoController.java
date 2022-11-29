package com.magdalena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.magdalena.entity.Producto;
import com.magdalena.service.CategoriaService;
import com.magdalena.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	//Los variables web (models) ingresadas en esta funcion estaran disponibles en todas las paginas
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("categorias", categoriaService.findAll());
	}
	
	// url (/producto) lista todos los productos en la base de datos
	@GetMapping
	public String mantProducto(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "Producto/mantProducto";
	}
	
	// url(/producto/consulta?descripcion) lista los productos por descripcion
	@GetMapping("/consulta")
	public String consultaxDescripcion(@RequestParam String descripcion, Model model) {
		model.addAttribute("productos", productoService.findByDescripcionContaining(descripcion));
		
		return "Producto/mantProducto";
	}
	
	// url(/producto/registra) envia al usuario al formulario de registro, la web estara esperando un registro nuevo
	@GetMapping("/registra")
	public String registraForm(Model model) {
		model.addAttribute("producto", new Producto());
		
		return "Producto/formProducto";
	}
	
	// url(/producto/registra) type POST, registra un nuevo usuario a la base de datos y muestra mensaje de exito o error.
	@PostMapping("/registra")
	public String registra(@ModelAttribute Producto producto, RedirectAttributes redirect) {
		productoService.save(producto);
		
		redirect.addFlashAttribute("message", "Producto registrado o actualizado correctamente...");
		
		return "redirect:/producto";
	}
	
	// url(/producto/edita/{id}, envia al usuario al formulario de registro pero para editar al usuario seleccionado
	@GetMapping("/edita/{id}")
	public String edita(@PathVariable Long id, Model model) {
		model.addAttribute("producto", productoService.findById(id));
		
		return "Producto/formProducto";
	}
	
	// url(/producto/elimina) type POST, elimina un usuario de la base de datos y envia mensaje de exito o error
	@PostMapping("/elimina")
	public String elimina(@RequestParam(name = "idproducto") Long id, RedirectAttributes redirect) {
		productoService.delete(id);
		
		redirect.addFlashAttribute("message", "Producto eliminado correctamente...");
		
		return "redirect:/producto";
	}
	
	
}
