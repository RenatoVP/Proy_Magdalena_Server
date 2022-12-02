package com.magdalena.service;
import java.util.List;

import com.magdalena.entity.Cliente;

public interface ClienteService {

	   public List<Cliente> listarCliente();
	    
	      public void guardar(Cliente  cliente);
	    
	    
	    public void eliminar (Cliente  cliente);
	    
	    
	    public Cliente   encontrarCliente(Cliente  cliente);
	    
	  
}
