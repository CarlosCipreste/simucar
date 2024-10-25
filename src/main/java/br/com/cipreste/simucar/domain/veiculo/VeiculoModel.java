package br.com.cipreste.simucar.domain.veiculo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cipreste.simucar.domain.enums.VehicleType;
import br.com.cipreste.simucar.domain.reserva.ReservaModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_veiculos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VeiculoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive
    private Long id;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @Enumerated(EnumType.STRING)
    @NotNull
    private VehicleType tipo;

    @NotBlank
    @Size(min = 6, max = 7)
    @Pattern(regexp = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$", message = "A placa deve seguir o padrão LLLNLNN.")
    @JsonProperty("num_placa")
    private String numPlaca;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<ReservaModel> reservas = new ArrayList<>();
    

}
