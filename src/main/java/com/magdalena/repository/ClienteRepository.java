package com.magdalena.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.magdalena.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	@Query("select c from Cliente c where c.dni like %?1%")
	public List<Cliente> findAllBydni(String dni);
}
