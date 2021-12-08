package com.crud.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.crud.model.Cliente;

@Service
public class ClienteService {

	public static boolean validaCpfNoBanco(List<Cliente> listaClientes, Cliente cliente){				
		List<Cliente> clientesComCpfIgual;
		
		if(cliente.getCPF() == null || cliente.getCPF().isBlank()) {
			return true;
		}
		
		clientesComCpfIgual = listaClientes.stream()
			.filter(o -> o.getCPF().equals(cliente.getCPF()))
			.collect(Collectors.toList());
				
		if(clientesComCpfIgual.isEmpty()){
			return false;
		}
		return true;		
	}
	
	
}
