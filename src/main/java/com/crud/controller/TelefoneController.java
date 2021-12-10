package com.crud.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.model.Cliente;
import com.crud.model.Telefone;
import com.crud.repository.ClienteRepository;
import com.crud.repository.TelefoneRepository;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {

	
	@Autowired
	private TelefoneRepository telefoneRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	@PostMapping(value = { "/{id}" })
	@ResponseStatus(HttpStatus.CREATED)
	public Telefone adicionarTelefone(@PathVariable Long id, @RequestBody Telefone telefone) {		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		telefone.setCliente(cliente);
		return telefoneRepository.save(telefone);
	}

}
