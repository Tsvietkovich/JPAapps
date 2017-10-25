package com.tsvietkovich.bank;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String type;
    private Double balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="person_id")
    private Person person;
    @OneToOne
    private ExchangeRate rate;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction>transactions;

    public Account() {
    }

    public Account(String type, Double balance) {
        this.type = type;
        this.balance = balance;

    }
    public void addTransaction(Transaction action) {
        action.setAccount(this);
        transactions.add(action);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ExchangeRate getRate() {
        return rate;
    }

    public void setRate(ExchangeRate rate) {
        this.rate = rate;
    }



    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", balance=" + balance +
                ", person=" + person +
                ", rate=" + rate +
                ", transactions=" + transactions +
                '}';
    }
}
