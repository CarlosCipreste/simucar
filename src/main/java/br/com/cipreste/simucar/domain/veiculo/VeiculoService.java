package br.com.cipreste.simucar.domain.veiculo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cipreste.simucar.domain.handlers.exceptions.ResourceAlreadyExistsException;
import br.com.cipreste.simucar.domain.handlers.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<VeiculoDTO> findAll() {
        List<VeiculoModel> veiculos = veiculoRepository.findAll();
        List<VeiculoDTO> veiculoDTOs = veiculos.stream()
                .map(VeiculoDTO::toDTO)
                .collect(Collectors.toList());
        return veiculoDTOs;
    }

    @Transactional
    public VeiculoModel save(VeiculoModel veiculoModel) {

        Optional<VeiculoModel> veiculoExists = veiculoRepository.findByNumPlaca(veiculoModel.getNumPlaca());
        if (veiculoExists.isPresent())
            throw new ResourceAlreadyExistsException("Véiculo já cadastrado com esse número de placa");
        VeiculoModel savedVeiculo = veiculoRepository.save(veiculoModel);
        return savedVeiculo;

    }

    @Transactional
    public VeiculoModel update(VeiculoDTO veiculo) {
        Optional<VeiculoModel> veiculoExists = veiculoRepository.findByNumPlaca(veiculo.numPlaca());

        if (veiculoExists.isEmpty())
            throw new ResourceNotFoundException("Veiculo não encontrado pelo número da placa");

        VeiculoModel existingVeiculo = veiculoExists.get();
        existingVeiculo.setMarca(veiculo.marca());
        existingVeiculo.setModelo(veiculo.modelo());
        existingVeiculo.setTipo(veiculo.tipo());
        existingVeiculo.setNumPlaca(veiculo.numPlaca());

        return veiculoRepository.save(existingVeiculo);
    }

    public void delete(Long id) {
        Optional<VeiculoModel> veiculoExists = veiculoRepository.findById(id);
        if(veiculoExists.isEmpty()) throw new ResourceNotFoundException("Veiculo não encontrado no id " + id);
        veiculoRepository.deleteById(id);
    }

}
