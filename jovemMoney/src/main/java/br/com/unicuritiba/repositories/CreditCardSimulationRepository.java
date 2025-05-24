package br.com.unicuritiba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unicuritiba.models.CreditCardSimulation;
import br.com.unicuritiba.models.User;

public interface CreditCardSimulationRepository extends JpaRepository<CreditCardSimulation, Long> {
    List<CreditCardSimulation> findByUser(User user);

}
