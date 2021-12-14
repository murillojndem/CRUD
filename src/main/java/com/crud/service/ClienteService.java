package com.crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.crud.controller.dto.ClienteDTO;
import com.crud.controller.form.ClienteForm;
import com.crud.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.model.Cliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findById(Long id) {
		return clienteRepository
				.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
	}

	public static boolean validaCpfNoBanco(List<Cliente> listaClientes, ClienteForm cliente){
		List<Cliente> clientesComCpfIgual;
		
		if(cliente.getCpf() == null || cliente.getCpf().isEmpty()) {
			return true;
		}
		
		clientesComCpfIgual = listaClientes.stream()
			.filter(o -> o.getCPF().equals(cliente.getCpf()))
			.collect(Collectors.toList());

		return !clientesComCpfIgual.isEmpty();
	}

	public ClienteDTO save(ClienteForm clienteForm) {
		Cliente cliente = getCliente(clienteForm);
		return getClienteDTO(cliente);
	}

	private ClienteDTO getClienteDTO(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();
		dto.setNome(cliente.getNome());
		dto.setCpf(cliente.getCPF());
		dto.setDataNascimento(cliente.getDataNascimento());
		return dto;
	}

	private Cliente getCliente(ClienteForm clienteForm) {
		Cliente cliente = new Cliente();
		cliente.setNome(clienteForm.getNome());
		cliente.setDataNascimento(clienteForm.getDataNascimento());
		cliente.setCPF(clienteForm.getCpf());
		cliente = clienteRepository.save(cliente);
		return cliente;
	}

	public void deleteById(Long id) {
		clienteRepository.deleteById(id);
	}

	public ClienteDTO update(Long id, ClienteForm clienteForm) {
		Cliente clienteAutalizado = this.findById(id);
		setClienteAPartirDeClienteForm(clienteForm, clienteAutalizado);
		clienteAutalizado = clienteRepository.save(clienteAutalizado);
		return getClienteDTO(clienteAutalizado);
	}

	private void setClienteAPartirDeClienteForm(ClienteForm clienteForm, Cliente clienteAutalizado) {
		clienteAutalizado.setNome(clienteForm.getNome());
		clienteAutalizado.setCPF(clienteForm.getCpf());
		clienteAutalizado.setDataNascimento(clienteForm.getDataNascimento());
	}
}
