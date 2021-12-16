package com.crud.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	@Column(nullable = false)
	private String CPF;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "cliente")
	@JsonIgnore
	private List<Telefone> telefones;
	
}
