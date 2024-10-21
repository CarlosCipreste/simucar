package br.com.cipreste.simucar.domain.cliente;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RestController
@RequestMapping("clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listClientes() {
        List<ClienteDTO> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> saveCliente(@RequestBody @Valid ClienteModel cliente, UriComponentsBuilder builder) {
        ClienteModel saved = clienteService.save(cliente);
        var uri = builder.path("/clientes/{id}").buildAndExpand(saved.getId()).toUri(); 
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<?> updateCliente(@RequestBody @Valid ClienteModel cliente) {
        clienteService.update(cliente);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping
    public ResponseEntity<Void> deleteCliente(@RequestParam Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
