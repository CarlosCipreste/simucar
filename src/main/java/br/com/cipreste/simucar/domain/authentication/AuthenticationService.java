package br.com.cipreste.simucar.domain.authentication;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cipreste.simucar.domain.authentication.tokenJWT.TokenService;
import br.com.cipreste.simucar.domain.cliente.ClienteModel;
import br.com.cipreste.simucar.domain.cliente.ClienteRepository;
import br.com.cipreste.simucar.domain.enums.UserRole;
import br.com.cipreste.simucar.domain.handlers.exceptions.ResourceAlreadyExistsException;
import br.com.cipreste.simucar.domain.user.UserModel;
import br.com.cipreste.simucar.domain.user.UserRepository;
import jakarta.transaction.Transactional;

@Configuration
@Service
public class AuthenticationService {

    @Value("${api.security.token.secret}")
    private String SECRET;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public ClienteModel saveNewUser(RegisterDTO registerDTO) {

        Optional<UserDetails> userExists = userRepository.findByLogin(registerDTO.getLogin());
        if (userExists.isPresent())
            throw new ResourceAlreadyExistsException("UserDetails", "Login já existe!");

        UserModel user = new UserModel();
        user.setLogin(registerDTO.getLogin());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(UserRole.USER);

        user = userRepository.save(user);

        ClienteModel cliente = new ClienteModel(registerDTO, user);

        return clienteRepository.save(cliente);
    }

    public String authorizeUser(AuthenticationDTO authDTO) {
        var usernamePasssword = new UsernamePasswordAuthenticationToken(authDTO.username(), authDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePasssword);

        String token = tokenService.generateToken((UserModel) auth.getPrincipal());
        return token;
    }



}
