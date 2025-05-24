package br.com.unicuritiba.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unicuritiba.models.FinancingSimulation;

public interface FinancingSimulationRepository extends JpaRepository<FinancingSimulation, Long> {
    List<FinancingSimulation> findByUserId(Long userId);
}
