package com.magdalena.domain;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="tb_cliente")
public class Cliente implements Serializable {
	private static  final long serialVersionUID = 11;
	   @Id
	   @GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Long id; 
	
	@NotEmpty
	private String razonsocial;
	
	@NotEmpty
	@Size(min=8,max=8)
    private String dni;

    private int telefono; 
	
	@NotEmpty
    private String direccion;
}
