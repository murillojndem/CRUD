package com.crud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
