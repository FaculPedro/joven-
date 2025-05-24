package br.com.unicuritiba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unicuritiba.models.FinancingSimulation;
import br.com.unicuritiba.models.User;
import br.com.unicuritiba.repositories.FinancingSimulationRepository;
import br.com.unicuritiba.repositories.UserRepository;

@Service
public class FinancingSimulationService {

    @Autowired
    private FinancingSimulationRepository repository;

    @Autowired
    private UserRepository userRepository;

    public FinancingSimulation createSimulation(Long userId, FinancingSimulation sim) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) throw new RuntimeException("User not found");

        User user = userOpt.get();

        double P = sim.getItemPrice() - sim.getDownPayment(); // Valor financiado
        double i = sim.getMonthlyInterestRate() / 100.0;
        int n = sim.getMonths();
        double fees = sim.getAdditionalFees();

        // Cálculo da parcela com juros compostos
        double installment = (P * i) / (1 - Math.pow(1 + i, -n));
        double totalPaid = (installment * n) + fees;

        double totalInvested = totalPaid + sim.getDownPayment();
        double cet = ((totalInvested / sim.getItemPrice()) - 1) * 100;

        sim.setTotalAmountPaid(Math.round(totalPaid * 100.0) / 100.0);
        sim.setCet(Math.round(cet * 100.0) / 100.0);
        sim.setUser(user);

        return repository.save(sim);
    }

    public List<FinancingSimulation> getAllByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public FinancingSimulation getById(Long userId, Long simId) {
        FinancingSimulation sim = repository.findById(simId)
                .orElseThrow(() -> new RuntimeException("Simulation not found"));
        if (sim.getUser().getId() != userId)
            throw new RuntimeException("Simulation does not belong to this user");
        return sim;
    }

    public FinancingSimulation updateSimulation(Long userId, Long simId, FinancingSimulation newSim) {
        FinancingSimulation sim = getById(userId, simId);
        sim.setItemPrice(newSim.getItemPrice());
        sim.setDownPayment(newSim.getDownPayment());
        sim.setMonths(newSim.getMonths());
        sim.setMonthlyInterestRate(newSim.getMonthlyInterestRate());
        sim.setAdditionalFees(newSim.getAdditionalFees());
        return createSimulation(userId, sim); // recalcula e salva
    }

    public void deleteSimulation(Long userId, Long simId) {
        FinancingSimulation sim = getById(userId, simId);
        repository.delete(sim);
    }
}
