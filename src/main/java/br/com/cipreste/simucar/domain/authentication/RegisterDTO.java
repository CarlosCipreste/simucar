package br.com.cipreste.simucar.domain.authentication;

public record RegisterDTO(
        String login,
        String password,
        String nome,
        String sobrenome,
        String cpf,
        String email,
        String numeroCelular) {
}
