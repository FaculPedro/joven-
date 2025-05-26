package br.com.unicuritiba.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.unicuritiba.models.InterestComparisonSimulation;

public interface InterestComparisonSimulationRepository extends JpaRepository<InterestComparisonSimulation, Long> {
    List<InterestComparisonSimulation> findByUserId(Long userId);
}
