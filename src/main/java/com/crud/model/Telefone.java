package com.crud.model;

import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@ApiModelProperty(hidden = true)
	private Long id_telefone;	
	
	@ManyToOne(targetEntity = Cliente.class, cascade=CascadeType.PERSIST)
	@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", insertable = false, updatable = false)
	@ApiModelProperty(hidden = true)
	private Optional<Cliente> cliente;
	
	private String telefone;
	
	
}
