package com.magdalena.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magdalena.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	List<Producto> findByDescripcionContaining(String infix);
}
