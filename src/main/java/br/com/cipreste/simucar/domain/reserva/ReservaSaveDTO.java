package br.com.cipreste.simucar.domain.reserva;

import java.time.LocalDateTime;

public record ReservaSaveDTO(
        Long clienteId,
        Long veiculoId,
        LocalDateTime dataInicio,
        LocalDateTime dataFim
) {

}
