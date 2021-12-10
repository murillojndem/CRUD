package com.crud.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;


@Data
@Entity
public class Cliente {

	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@ApiModelProperty(hidden = true)
	@Getter(value = AccessLevel.PUBLIC)
	private Long id_cliente;
	
	@Column
	private String nome;
	
	//@CPF(message="cpf invalido")
	@Column(nullable = false)
	private String CPF;
	
	private Date dataDeNascimento;
	
	@ApiModelProperty(hidden = true)
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Telefone> telefone;
	
	
}
