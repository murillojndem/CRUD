package com.crud.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@ApiModelProperty(hidden = true)
	private Long id;
	
	@Column
	private String nome;
	
	//@CPF(message="cpf invalido")
	@Column(nullable = false)
	private String CPF;
	
	@Column
	private Date dataDeNascimento;
	
	@Column
	private String telefone;
	
	
}
