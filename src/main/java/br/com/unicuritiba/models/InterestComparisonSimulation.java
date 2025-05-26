package br.com.unicuritiba.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class InterestComparisonSimulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double initialAmount;
    private double interestRate;
    private int months;

    private double simpleInterestTotal;
    private double compoundInterestTotal;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getInitialAmount() { return initialAmount; }
    public void setInitialAmount(double initialAmount) { this.initialAmount = initialAmount; }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }

    public int getMonths() { return months; }
    public void setMonths(int months) { this.months = months; }

    public double getSimpleInterestTotal() { return simpleInterestTotal; }
    public void setSimpleInterestTotal(double simpleInterestTotal) { this.simpleInterestTotal = simpleInterestTotal; }

    public double getCompoundInterestTotal() { return compoundInterestTotal; }
    public void setCompoundInterestTotal(double compoundInterestTotal) { this.compoundInterestTotal = compoundInterestTotal; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
