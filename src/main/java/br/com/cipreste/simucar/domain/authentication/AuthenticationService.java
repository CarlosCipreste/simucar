package br.com.cipreste.simucar.domain.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cipreste.simucar.domain.cliente.ClienteModel;
import br.com.cipreste.simucar.domain.cliente.ClienteRepository;
import br.com.cipreste.simucar.domain.enums.UserRole;
import br.com.cipreste.simucar.domain.user.UserModel;
import br.com.cipreste.simucar.domain.user.UserRepository;
import jakarta.validation.Valid;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteModel saveNewUserandCliente(RegisterDTO registerDTO) {
        // Criação do UserModel
        UserModel user = new UserModel();
        user.setLogin(registerDTO.login());
        user.setPassword(registerDTO.password()); // Use uma codificação segura para a senha, como BCrypt
        user.setRole(UserRole.USER); // Role padrão, ajuste conforme necessário

        // Criação do ClienteModel
        ClienteModel cliente = new ClienteModel();
        cliente.setNome(registerDTO.nome());
        cliente.setSobrenome(registerDTO.sobrenome());
        cliente.setCpf(registerDTO.cpf());
        cliente.setEmail(registerDTO.email());
        cliente.setNumeroCelular(registerDTO.numeroCelular());
        cliente.setUser(user); // Relaciona o cliente ao usuário

        // Salva primeiro o cliente, e depois o usuário associado
        user.setCliente(cliente);
        return clienteRepository.save(cliente); // Salva o cliente e o usuário devido à cascata
    }

    public void authorizeUser(@Valid AuthenticationDTO authDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authorizeUser'");
    }

}
