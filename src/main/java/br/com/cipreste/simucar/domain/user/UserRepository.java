package br.com.cipreste.simucar.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserModel, Long>{
    Optional<UserDetails> findByLogin(String login);
}
