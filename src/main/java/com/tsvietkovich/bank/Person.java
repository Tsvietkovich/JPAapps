package com.tsvietkovich.bank;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String secondName;
    private String passportSeries;
    private Integer passportNumber;
    private String telephone;
    private String email;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Account> accounts;

    public Person() {
    }

    public Person(String firstName, String secondName, String passportSeries, Integer passportNumber, String telephone, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.telephone = telephone;
        this.email = email;
    }

    public void addAccount(Account account) {
        account.setPerson(this);
        accounts.add(account);
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public Integer getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Integer passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber=" + passportNumber +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
