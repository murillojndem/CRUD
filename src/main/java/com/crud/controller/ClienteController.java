package com.crud.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.crud.model.Cliente;
import com.crud.model.Telefone;
import com.crud.repository.ClienteRepository;
import com.crud.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Cliente> acharClientePorId(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.orElse(null) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);			
		} else {
			return clienteRepository.findById(id);
		}
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente, @RequestBody Telefone telefone) {		
		List<Cliente> listaDeClientes = clienteRepository.findAll();
		boolean validaCpf = ClienteService.validaCpfNoBanco(listaDeClientes, cliente);
		if(validaCpf == true) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		} else {
			return clienteRepository.save(cliente);
		}
	}
	
	
	@DeleteMapping(value = { "/{id}" })
	public void deletar(@PathVariable Long id) {
		try {
		clienteRepository.deleteById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = { "/{id}" })
	@ResponseBody
	public Cliente update(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {		
		try {
			Cliente clienteAtualizado = clienteRepository.getById(id);
			clienteAtualizado.setNome(cliente.getNome());
			return clienteRepository.save(clienteAtualizado);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
