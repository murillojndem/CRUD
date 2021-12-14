package com.crud.service;

import com.crud.controller.dto.ClienteDTO;
import com.crud.controller.dto.TelefoneDTO;
import com.crud.controller.form.TelefoneForm;
import com.crud.model.Cliente;
import com.crud.model.Telefone;
import com.crud.repository.ClienteRepository;
import com.crud.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private ClienteRepository clienteRepository;


    public TelefoneDTO salvar(TelefoneForm telefoneForm, Long idCliente) {
        Cliente cliente = buscarCliente(idCliente);
        Telefone telefone = getTelefone(telefoneForm, cliente);
        telefoneRepository.save(telefone);

        return getTelefoneDTO(cliente, telefone);
    }

    private TelefoneDTO getTelefoneDTO(Cliente cliente, Telefone telefone) {
        TelefoneDTO dto = new TelefoneDTO();
        dto.setTelefone(telefone.getTelefone());
        dto.setCliente(getClienteDTO(cliente));
        return dto;
    }

    private ClienteDTO getClienteDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setNome(cliente.getNome());
        dto.setCpf(cliente.getCPF());
        dto.setDataNascimento(cliente.getDataNascimento());
        return dto;
    }

    private Telefone getTelefone(TelefoneForm telefoneForm, Cliente cliente) {
        Telefone telefone = new Telefone();
        telefone.setTelefone(telefoneForm.getTelefone());
        telefone.setCliente(cliente);
        return telefone;
    }

    private Cliente buscarCliente(Long idCliente) {
       return clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
    }
}
