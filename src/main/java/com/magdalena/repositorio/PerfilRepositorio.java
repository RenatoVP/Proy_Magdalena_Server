package com.magdalena.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magdalena.entidad.Perfil;

@Repository
public interface PerfilRepositorio extends JpaRepository<Perfil, Integer> {

}
