package br.com.cipreste.simucar.domain.authentication;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterDTO {

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @CPF
    private String cpf;

    @Email
    private String email;

    @JsonProperty("numero_celular")
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "Número de celular inválido")
    private String numeroCelular;
}
