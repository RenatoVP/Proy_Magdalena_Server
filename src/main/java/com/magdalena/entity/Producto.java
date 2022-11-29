package com.magdalena.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_producto")
@Data
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 90, nullable = false)
	private String descripcion;
	
	@Column(nullable = false)
	private double precio;
	
	@Column(columnDefinition = "int default 0")
	private int cantidad;
	
	@ManyToOne
	@JoinColumn(name = "idcategoria")
	private Categoria categoria;
}
