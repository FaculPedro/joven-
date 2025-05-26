package br.com.unicuritiba.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class FinancingSimulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double itemPrice;
    private double downPayment;
    private int months;
    private double monthlyInterestRate;
    private double additionalFees;

    private double totalAmountPaid;
    private double cet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getItemPrice() { return itemPrice; }
    public void setItemPrice(double itemPrice) { this.itemPrice = itemPrice; }

    public double getDownPayment() { return downPayment; }
    public void setDownPayment(double downPayment) { this.downPayment = downPayment; }

    public int getMonths() { return months; }
    public void setMonths(int months) { this.months = months; }

    public double getMonthlyInterestRate() { return monthlyInterestRate; }
    public void setMonthlyInterestRate(double monthlyInterestRate) { this.monthlyInterestRate = monthlyInterestRate; }

    public double getAdditionalFees() { return additionalFees; }
    public void setAdditionalFees(double additionalFees) { this.additionalFees = additionalFees; }

    public double getTotalAmountPaid() { return totalAmountPaid; }
    public void setTotalAmountPaid(double totalAmountPaid) { this.totalAmountPaid = totalAmountPaid; }

    public double getCet() { return cet; }
    public void setCet(double cet) { this.cet = cet; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
