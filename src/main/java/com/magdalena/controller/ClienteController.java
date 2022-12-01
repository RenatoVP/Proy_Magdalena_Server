package com.magdalena.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.magdalena.Dao.ClienteDAO;
import com.magdalena.Servicio.ClienteService;
import com.magdalena.domain.Cliente;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ClienteController {

	 @Autowired
	 private ClienteDAO clienteD;
	    
    @Autowired
 private ClienteService clienteService;
    
    @GetMapping("/Cliente")
    public String Clientes(Model model){
         var cliente =clienteService.listarCliente();
         model.addAttribute("cliente", cliente);    
          return "Cliente/Clientes";
    }
    
    @GetMapping("Cliente/agregar")
    public String agregar(Cliente  cliente){
        
    return "Cliente/ModificaCli";
    }
    
    @PostMapping("Cliente/guardar")
    public  String guardar(@Valid  Cliente cliente,Errors errores){
    	if(errores.hasErrors()){
            return "Cliente/ModificaCli";
        }  
        clienteService.guardar(cliente);
        return "redirect:/Cliente";
        
      }
    
    @GetMapping("Cliente/editar/{id}")
    public String editar(Cliente cliente,Model model){
    cliente=clienteService.encontrarCliente(cliente);
    model.addAttribute("cliente", cliente); 
    return "Cliente/ModificaCli";
}
    
    @GetMapping("Cliente/Eliminar/{id}")
    public String Eliminar(Cliente cliente){
        clienteService.eliminar(cliente);
    return "redirect:/Cliente";
    }
    
    
    @GetMapping("Cliente/Consulta")
	public String consultaClieForm(Cliente cliente) {
		return "Cliente/ConsultaCliente";
	}
	
	@PostMapping("Cliente/Consulta")
	public String consultaCliente(@RequestParam String dniCli, Model model) {
		List<Cliente> lstClientes;
		
			lstClientes = clienteD.findAllBydni(dniCli);
		
		model.addAttribute("lstClientes", lstClientes);
		return "Cliente/ConsultaCliente";
	}
    
}
