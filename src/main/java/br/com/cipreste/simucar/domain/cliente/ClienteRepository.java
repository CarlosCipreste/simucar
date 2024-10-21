package br.com.cipreste.simucar.domain.cliente;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long>{

    Optional<ClienteModel> findByCpf(String cpf);
    
}
