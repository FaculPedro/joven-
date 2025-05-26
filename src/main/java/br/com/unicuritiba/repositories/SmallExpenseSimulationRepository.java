package br.com.unicuritiba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unicuritiba.models.SmallExpenseSimulation;

public interface SmallExpenseSimulationRepository extends JpaRepository<SmallExpenseSimulation, Long> {
    List<SmallExpenseSimulation> findByUserId(Long userId);
}
