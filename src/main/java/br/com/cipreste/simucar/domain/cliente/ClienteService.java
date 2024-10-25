package br.com.cipreste.simucar.domain.cliente;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cipreste.simucar.domain.handlers.exceptions.ResourceAlreadyExistsException;
import br.com.cipreste.simucar.domain.handlers.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.*;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<ClienteDTO> findAll() {
        List<ClienteModel> clientes = clienteRepository.findAll();
        List<ClienteDTO> clientesDTOs = clientes.stream()
                .map(ClienteDTO::toDTO)
                .collect(Collectors.toList());
        return clientesDTOs;
    }

    @Transactional
    public ClienteModel save(ClienteModel cliente) {
        Optional<ClienteModel> clienteExists = clienteRepository.findByCpf(cliente.getCpf());

        if (clienteExists.isPresent()) {
            throw new ResourceAlreadyExistsException("Cliente","Cliente já existente nesse CPF.");
        }

        ClienteModel savedCliente = clienteRepository.save(cliente);
        return savedCliente;
    }

    @Transactional
    public ClienteModel update(ClienteModel cliente) {
        Optional<ClienteModel> clienteExists = clienteRepository.findById(cliente.getId());

        if (clienteExists.isEmpty())
            throw new ResourceNotFoundException("Cliente","Cliente não encontrado");

        // Atualiza os dados do cliente existente
        ClienteModel existingCliente = clienteExists.get();
        existingCliente.setNome(cliente.getNome());
        existingCliente.setSobrenome(cliente.getSobrenome());
        existingCliente.setCpf(cliente.getCpf());
        existingCliente.setEmail(cliente.getEmail());
        existingCliente.setNumeroCelular(cliente.getNumeroCelular());
        //existingCliente.setUser(cliente.getUser()); TODO

        return clienteRepository.save(existingCliente);
    }

    public void delete(Long id) {
        Optional<ClienteModel> clienteExists = clienteRepository.findById(id);
        if(clienteExists.isEmpty()) throw new ResourceNotFoundException("Cliente","Cliente não encontrado no id especificado");
        clienteRepository.deleteById(id);
    }

    public ClienteDTO findById(Long id) {
        Optional<ClienteModel> clienteExists = clienteRepository.findById(id);
        if(clienteExists.isEmpty()) throw new ResourceNotFoundException("Cliente", "Cliente não encontrado no id especificado");
        return ClienteDTO.toDTO(clienteExists.get());
    }
}
