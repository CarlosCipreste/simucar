package br.com.cipreste.simucar.domain.cliente;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cipreste.simucar.domain.authentication.RegisterDTO;
import br.com.cipreste.simucar.domain.reserva.ReservaModel;
import br.com.cipreste.simucar.domain.user.UserModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_clientes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String nome;

    @NotBlank
    @Size(max = 60)
    private String sobrenome;

    @CPF
    @NotBlank
    @Column(unique = true)
    private String cpf;

    @Email
    @NotBlank
    private String email;

    @JsonProperty("numero_celular")
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "Numero de celular invalido")
    private String numeroCelular;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<ReservaModel> reservas = new ArrayList<>();

    public ClienteModel(RegisterDTO registerDTO, UserModel user) {
        this.nome = registerDTO.getNome();
        this.sobrenome = registerDTO.getSobrenome();
        this.cpf = registerDTO.getCpf();
        this.email = registerDTO.getEmail();
        this.numeroCelular = registerDTO.getNumeroCelular();
        this.user = user;
    }

}
