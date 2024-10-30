package br.com.cipreste.simucar.domain.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cipreste.simucar.domain.handlers.exceptions.ResourceNotFoundException;

@Service
public class AuthorizationService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> user = userRepository.findByLogin(username);
        if(user.isEmpty()) throw new ResourceNotFoundException("UserService", "Usuário não encontrado com o login especificado");
        return user.get();
    }

}
