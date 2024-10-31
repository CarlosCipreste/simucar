package br.com.cipreste.simucar.domain.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cipreste.simucar.domain.cliente.ClienteModel;
import br.com.cipreste.simucar.domain.cliente.ClienteRepository;
import br.com.cipreste.simucar.domain.enums.UserRole;
import br.com.cipreste.simucar.domain.user.UserModel;
import br.com.cipreste.simucar.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public ClienteModel saveNewUserandCliente(RegisterDTO registerDTO) {
        
        Optional<UserDetails> userExists = userRepository.findByLogin(registerDTO.getLogin());
        if (userExists.isPresent()) throw new RuntimeException("Login já existe!");
        
        UserModel user = new UserModel();
        user.setLogin(registerDTO.getLogin());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(UserRole.USER);

        user = userRepository.save(user);

        ClienteModel cliente = new ClienteModel(registerDTO, user);

        return clienteRepository.save(cliente);
    }

    public void authorizeUser(@Valid AuthenticationDTO authDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authorizeUser'");
    }

}
