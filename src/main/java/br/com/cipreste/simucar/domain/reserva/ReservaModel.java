package br.com.cipreste.simucar.domain.reserva;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cipreste.simucar.domain.cliente.ClienteModel;
import br.com.cipreste.simucar.domain.veiculo.VeiculoModel;
import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_reservas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private VeiculoModel veiculo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteModel cliente;

    @JsonProperty("data_inicio")
    @NotNull
    @FutureOrPresent
    private LocalDateTime dataInicio;

    @JsonProperty("carro_pego")
    private boolean carroPego = false;

    @NotNull
    @FutureOrPresent
    @JsonProperty("data_fim")
    private LocalDateTime dataFim;

    @JsonProperty("carro_devolvido")
    @Nullable
    private boolean carroDevolvido = false;

    @JsonProperty("reserva_ativa")
    @Nullable
    private boolean reservaAtiva = true;

    @AssertTrue(message = "A data de fim deve ser posterior à data de início")
    public boolean isDataFimPosterior() {
        return dataFim != null && dataInicio != null && dataFim.isAfter(dataInicio);
    }

    public void setReservaAtiva(boolean reservaAtiva) {
        if (!this.carroPego) {
            this.reservaAtiva = reservaAtiva;
        } else {
            throw new IllegalStateException("Não é possível modificar a reserva ativa após o carro ter sido pego.");
        }
    }
}
