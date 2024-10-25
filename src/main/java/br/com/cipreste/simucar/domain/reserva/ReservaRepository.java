package br.com.cipreste.simucar.domain.reserva;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservaRepository extends JpaRepository<ReservaModel, Long> {

    @Query("SELECT r FROM ReservaModel r WHERE r.cliente.id = :clienteId AND r.veiculo.id = :veiculoId AND " +
            "(:dataInicio BETWEEN r.dataInicio AND r.dataFim OR :dataFim BETWEEN r.dataInicio AND r.dataFim)")
    Optional<ReservaModel> findByClienteAndVeiculoAndPeriodo(
            @Param("clienteId") Long clienteId,
            @Param("veiculoId") Long veiculoId,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim);

}
