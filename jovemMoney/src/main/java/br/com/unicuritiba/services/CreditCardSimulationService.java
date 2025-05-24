package br.com.unicuritiba.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.unicuritiba.models.CreditCardSimulation;
import br.com.unicuritiba.models.User;
import br.com.unicuritiba.repositories.CreditCardSimulationRepository;

@Service
public class CreditCardSimulationService {

    private final CreditCardSimulationRepository repository;

    public CreditCardSimulationService(CreditCardSimulationRepository repository) {
        this.repository = repository;
    }

    public List<CreditCardSimulation> getAllByUser(User user) {
        return repository.findByUser(user);
    }

    public Optional<CreditCardSimulation> getByIdAndUser(Long id, User user) {
        return repository.findById(id)
                .filter(sim -> sim.getUser().getId() == user.getId());
    }

    public CreditCardSimulation save(CreditCardSimulation simulation) {
        // Calcula o total a pagar antes de salvar
        double total = calculateTotalAmount(simulation.getInitialAmount(), simulation.getInterestRate(),
                simulation.getPaymentType(), simulation.getMonths());
        simulation.setTotalAmountToPay(total);
        return repository.save(simulation);
    }

    public Optional<CreditCardSimulation> update(Long id, CreditCardSimulation updatedSim, User user) {
        return repository.findById(id)
                .filter(sim -> sim.getUser().getId() == user.getId())
                .map(sim -> {
                    sim.setInitialAmount(updatedSim.getInitialAmount());
                    sim.setInterestRate(updatedSim.getInterestRate());
                    sim.setPaymentType(updatedSim.getPaymentType());
                    sim.setMonths(updatedSim.getMonths());
                    double total = calculateTotalAmount(sim.getInitialAmount(), sim.getInterestRate(),
                            sim.getPaymentType(), sim.getMonths());
                    sim.setTotalAmountToPay(total);
                    return repository.save(sim);
                });
    }

    public boolean delete(Long id, User user) {
        return repository.findById(id)
                .filter(sim -> sim.getUser().getId() == user.getId())
                .map(sim -> {
                    repository.delete(sim);
                    return true;
                }).orElse(false);
    }

    // Método para calcular o valor total a pagar, considerando o tipo de pagamento
    private double calculateTotalAmount(double principal, double monthlyInterest, String paymentType, int months) {
        double total = principal;
        double monthlyRate = monthlyInterest / 100.0;

        switch (paymentType.toLowerCase()) {
            case "minimum":
                // Pagamento mínimo: juros rotativos, dívida cresce exponencialmente
                for (int i = 0; i < months; i++) {
                    total += total * monthlyRate;
                    // assumindo que o pagamento mínimo é apenas para cobrir os juros, sem amortização
                }
                break;

            case "partial":
                // Pagamento parcial: assume que paga 50% da dívida mensal, o restante sofre juros
                double monthlyPayment = principal * 0.5;
                for (int i = 0; i < months; i++) {
                    total += total * monthlyRate;
                    total -= monthlyPayment;
                    if (total < 0) total = 0;
                }
                break;

            case "full":
                // Quitação total: paga o principal + juros acumulados, simula juros simples
                total = principal + (principal * monthlyRate * months);
                break;

            default:
                // Caso desconhecido, assume pagamento total simples
                total = principal + (principal * monthlyRate * months);
                break;
        }
        return Math.round(total * 100.0) / 100.0; // arredonda para 2 casas decimais
    }
}
