package com.magdalena.Servicio;
import java.util.List;

import com.magdalena.domain.Cliente;

public interface ClienteService {

	   public List<Cliente> listarCliente();
	    
	      public void guardar(Cliente  cliente);
	    
	    
	    public void eliminar (Cliente  cliente);
	    
	    
	    public Cliente   encontrarCliente(Cliente  cliente);
	    
	  
}
