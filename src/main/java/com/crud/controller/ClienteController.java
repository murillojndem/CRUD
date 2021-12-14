package com.crud.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.crud.controller.dto.ClienteDTO;
import com.crud.controller.form.ClienteForm;
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
import com.crud.repository.ClienteRepository;
import com.crud.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<Cliente> listar() {
		return clienteService.findAll();
	}

	@GetMapping("/{id}")
	public Cliente acharClientePorId(@PathVariable Long id) {
		return clienteService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteDTO adicionar(@RequestBody ClienteForm clienteForm) {
		List<Cliente> listaDeClientes = clienteService.findAll();
		boolean validaCpf = ClienteService.validaCpfNoBanco(listaDeClientes, clienteForm);
		if(validaCpf) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return clienteService.save(clienteForm);
	}
	
	
	@DeleteMapping(value = { "/{id}" })
	public void deletar(@PathVariable Long id) {
		try {
			clienteService.deleteById(id);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = { "/{id}" })
	@ResponseBody
	public ClienteDTO update(@PathVariable Long id, @Valid @RequestBody ClienteForm clienteForm) {
		try {
			return clienteService.update(id, clienteForm);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
