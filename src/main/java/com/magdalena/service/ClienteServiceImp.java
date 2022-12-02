package com.magdalena.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magdalena.entity.Cliente;
import com.magdalena.repository.ClienteRepository;

@Service
public class ClienteServiceImp implements ClienteService {

	  @Autowired
	    private  ClienteRepository clienteDAO;
	  
	@Override
	 @Transactional(readOnly = true)
	public List<Cliente> listarCliente() {
		return  (List<Cliente>) clienteDAO.findAll();
	}

	@Override
	@Transactional
	public void guardar(Cliente cliente) {
		clienteDAO.save(cliente);
		
	}

	@Override
    @Transactional
	public void eliminar(Cliente cliente) {
		clienteDAO.delete(cliente);
		
	}

	@Override
	public Cliente encontrarCliente(Cliente cliente) {
		 return clienteDAO.findById((long) cliente.getId()).orElse(null);
	}

	

}
