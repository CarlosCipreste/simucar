package br.com.cipreste.simucar.domain.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cipreste.simucar.domain.cliente.ClienteModel;
import br.com.cipreste.simucar.domain.cliente.ClienteRepository;
import br.com.cipreste.simucar.domain.enums.UserRole;
import br.com.cipreste.simucar.domain.user.UserModel;
import br.com.cipreste.simucar.domain.user.UserRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> authorizeUser(@RequestBody @Valid AuthenticationDTO authDTO) {
        authenticationService.authorizeUser(authDTO);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
        

        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

}