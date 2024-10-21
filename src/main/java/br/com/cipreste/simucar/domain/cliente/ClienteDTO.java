package br.com.cipreste.simucar.domain.cliente;

public record ClienteDTO(
        String nome,
        String sobrenome,
        String email
// UserModel user TODO
) {

    public static ClienteDTO toDTO(ClienteModel cliente) {
        return new ClienteDTO(cliente.getNome(), cliente.getSobrenome(), cliente.getEmail());
    }

}
