package br.com.unicuritiba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unicuritiba.models.InterestComparisonSimulation;
import br.com.unicuritiba.models.User;
import br.com.unicuritiba.repositories.InterestComparisonSimulationRepository;
import br.com.unicuritiba.repositories.UserRepository;

@Service
public class InterestComparisonSimulationService {

    @Autowired
    private InterestComparisonSimulationRepository repository;

    @Autowired
    private UserRepository userRepository;

    public InterestComparisonSimulation createSimulation(Long userId, InterestComparisonSimulation sim) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = userOpt.get();

        double rate = sim.getInterestRate() / 100.0;
        double P = sim.getInitialAmount();
        int n = sim.getMonths();

        double simple = P + (P * rate * n);
        double compound = P * Math.pow(1 + rate, n);

        sim.setSimpleInterestTotal(Math.round(simple * 100.0) / 100.0);
        sim.setCompoundInterestTotal(Math.round(compound * 100.0) / 100.0);
        sim.setUser(user);

        return repository.save(sim);
    }

    public List<InterestComparisonSimulation> getAllByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public InterestComparisonSimulation getById(Long userId, Long simId) {
        InterestComparisonSimulation sim = repository.findById(simId)
                .orElseThrow(() -> new RuntimeException("Simulation not found"));
        if (sim.getUser().getId() != userId) {
            throw new RuntimeException("Simulation does not belong to this user");
        }
        return sim;
    }

    public InterestComparisonSimulation updateSimulation(Long userId, Long simId, InterestComparisonSimulation newSim) {
        InterestComparisonSimulation sim = getById(userId, simId);
        sim.setInitialAmount(newSim.getInitialAmount());
        sim.setInterestRate(newSim.getInterestRate());
        sim.setMonths(newSim.getMonths());

        return createSimulation(userId, sim); // reprocessa os valores
    }

    public void deleteSimulation(Long userId, Long simId) {
        InterestComparisonSimulation sim = getById(userId, simId);
        repository.delete(sim);
    }
}
