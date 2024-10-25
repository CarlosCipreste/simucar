package br.com.cipreste.simucar.domain.reserva;

import java.time.LocalDateTime;

import br.com.cipreste.simucar.domain.enums.VehicleType;

public record ReservaResponseDTO(
        String clienteNome,
        String clienteSobrenome,
        String veiculoMarca,
        String veiculoModelo,
        VehicleType veiculoTipo,
        LocalDateTime dataInicio,
        LocalDateTime dataFim

) {
    public static ReservaResponseDTO toDTO(ReservaModel reserva) {
        return new ReservaResponseDTO(
                reserva.getCliente().getNome(),
                reserva.getCliente().getSobrenome(),
                reserva.getVeiculo().getMarca(),
                reserva.getVeiculo().getModelo(),
                reserva.getVeiculo().getTipo(),
                reserva.getDataInicio(),
                reserva.getDataFim()
                );
    }
}
