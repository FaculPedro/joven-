package br.com.unicuritiba.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "credit_card_simulations")
public class CreditCardSimulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    private Double initialAmount;

    @Column(nullable = false)
    private Double interestRate; // monthly interest in percentage, e.g., 10 for 10%

    @Column(nullable = false)
    private String paymentType; // "minimum", "partial", or "full"

    @Column(nullable = false)
    private Integer months; // number of months to simulate

    @Column(nullable = false)
    private Double totalAmountToPay; // result after simulation

    public CreditCardSimulation() {
    }

    public CreditCardSimulation(User user, Double initialAmount, Double interestRate, String paymentType, Integer months, Double totalAmountToPay) {
        this.user = user;
        this.initialAmount = initialAmount;
        this.interestRate = interestRate;
        this.paymentType = paymentType;
        this.months = months;
        this.totalAmountToPay = totalAmountToPay;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(Double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Double getTotalAmountToPay() {
        return totalAmountToPay;
    }

    public void setTotalAmountToPay(Double totalAmountToPay) {
        this.totalAmountToPay = totalAmountToPay;
    }
}
