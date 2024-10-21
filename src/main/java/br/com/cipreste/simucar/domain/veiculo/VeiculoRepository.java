package br.com.cipreste.simucar.domain.veiculo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<VeiculoModel, Long>{

    Optional<VeiculoModel> findByNumPlaca(String numPlaca);
    
}
