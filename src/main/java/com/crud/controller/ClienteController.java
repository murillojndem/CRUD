package com.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.model.Cliente;
import com.crud.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@GetMapping
	public List <Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Cliente> acharClientePorId(@PathVariable Long id) {
		return clienteRepository.findById(id);
	}	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@DeleteMapping(value ={"/{id}"})
	@ResponseStatus(HttpStatus.GONE)
	public void deletar(@PathVariable Long id) {
		clienteRepository.deleteById(id);
	}
	
	@PutMapping(value ={"/{id}"})
	public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente clienteAtualizado = clienteRepository.getById(id);
		clienteAtualizado.setNome(cliente.getNome());
		
		return clienteRepository.save(clienteAtualizado);
	}
	
	
	
}
