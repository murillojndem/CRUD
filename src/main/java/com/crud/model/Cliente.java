package com.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;


@Data
@Entity
public class Cliente {

	@Id
//	@GeneratedValue(generator = "increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@Column
	private String nome;
	
	
}
