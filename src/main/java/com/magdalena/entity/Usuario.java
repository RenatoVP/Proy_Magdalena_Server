package com.magdalena.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Entity
@Table(name = "tb_usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Data
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 35, nullable = false)
	private String nombre;
	
	@Column(length = 50, nullable = false)
	private String apellidos;
	
	@Column(length = 35, nullable = false)
	private String username;
	
	@Column(length = 255, nullable = false)
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "tb_usuario_rol", 
			joinColumns = @JoinColumn(name = "idusuario"),
			inverseJoinColumns = @JoinColumn(name = "idrol")
	)
	private Set<Rol> roles = new HashSet<Rol>();

}
