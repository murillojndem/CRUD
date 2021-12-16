package com.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.crud.model.Cliente;
import com.crud.repository.ClienteRepository;

class ClienteServiceTest {
	
	private static final Long ID = 1L;
	private static final String NOME = "Ze das couves";
	private static final String CPF = "321598465";
	private static final LocalDate DATANASCIMENTO = LocalDate.of(1990, 12, 14);
	
	@InjectMocks
	@Autowired
	private ClienteService clienteService;
	
	@Mock
	@Autowired
	private ClienteRepository clienteRepository;
	
	private Cliente cliente;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		startCliente();
	}

	@Test
	final void whenFindByIdRetunClienteInstance() {
		Mockito.when(clienteRepository
		       .findById(Mockito.anyLong()))
		       .thenReturn(Optional.of(cliente));
		
		Cliente response = clienteService.findById(ID);
		
		assertEquals(Cliente.class, response.getClass());
	}
	
	private void startCliente() {
		cliente = new Cliente();
		cliente.setId(ID);
		cliente.setNome(NOME);
		cliente.setCPF(CPF);
		cliente.setDataNascimento(DATANASCIMENTO);
	}

}
