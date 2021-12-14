package com.crud.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Cliente cliente;
	
	private String telefone;
	
	
}
