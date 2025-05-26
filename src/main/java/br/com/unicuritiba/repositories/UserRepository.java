package br.com.unicuritiba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unicuritiba.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpf(String cpf);
}
