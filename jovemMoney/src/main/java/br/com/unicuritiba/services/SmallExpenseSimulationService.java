package br.com.unicuritiba.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unicuritiba.models.SmallExpenseSimulation;
import br.com.unicuritiba.repositories.SmallExpenseSimulationRepository;

@Service
public class SmallExpenseSimulationService {

    @Autowired
    private SmallExpenseSimulationRepository repository;

    public List<SmallExpenseSimulation> getAllByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public SmallExpenseSimulation getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public SmallExpenseSimulation save(SmallExpenseSimulation simulation) {
        return repository.save(simulation);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public SmallExpenseSimulation update(Long id, SmallExpenseSimulation simulation) {
        simulation.setId(id);
        return repository.save(simulation);
    }
}
