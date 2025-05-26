package br.com.unicuritiba.models;

import jakarta.persistence.*;

@Entity
@Table(name = "small_expense_simulations")
public class SmallExpenseSimulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double itemPrice;       // price per item (e.g., coffee)
    private int frequencyPerWeek;   // how many times per week the item is bought
    private double monthlyInterestRate; // investment interest rate (monthly, in %)

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Calculated fields - not stored in DB, but returned in DTO or via @Transient
    @Transient
    private double annualSpent;

    @Transient
    private double investedAmount;

    @Transient
    private double finalAmountWithInterest;

    // Constructors, getters and setters

    public SmallExpenseSimulation() {
    }

    // Getters and setters...

    public double getAnnualSpent() {
        return itemPrice * frequencyPerWeek * 52; // 52 weeks in a year
    }

    public double getInvestedAmount() {
        return itemPrice * frequencyPerWeek * 4; // approx 4 weeks/month
    }

    public double getFinalAmountWithInterest() {
        double monthlyRate = monthlyInterestRate / 100;
        int months = 12;
        double invested = getInvestedAmount();
        // Formula for future value of monthly investments:
        // FV = invested * [((1 + r)^n - 1) / r]
        if (monthlyRate == 0) {
            return invested * months;
        }
        return invested * (Math.pow(1 + monthlyRate, months) - 1) / monthlyRate;
    }

    // Getters and setters for id, itemPrice, frequencyPerWeek, monthlyInterestRate, user

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getFrequencyPerWeek() {
        return frequencyPerWeek;
    }

    public void setFrequencyPerWeek(int frequencyPerWeek) {
        this.frequencyPerWeek = frequencyPerWeek;
    }

    public double getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    public void setMonthlyInterestRate(double monthlyInterestRate) {
        this.monthlyInterestRate = monthlyInterestRate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
