package br.com.cipreste.simucar.domain.veiculo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping("veiculos")
public class VeiculoController {
    
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> findAllVeiculos() {
        return ResponseEntity.ok().body(veiculoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> findVeiculoById(@RequestParam @Positive Long id){
        return ResponseEntity.ok(veiculoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> saveVeiculo(@RequestBody @Valid VeiculoModel veiculo, UriComponentsBuilder builder) {
        veiculoService.save(veiculo);
        var uri = builder.path("veiculos/{id}").buildAndExpand(veiculo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<?> updateVeiculo(@RequestBody @Valid VeiculoDTO veiculoUpdate) {
        veiculoService.update(veiculoUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@RequestParam @Positive Long id) {
        veiculoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
