package com.tsvietkovich.bank;


import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Service {
    private EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("JPAapps");
    private EntityManager entityManager = managerFactory.createEntityManager();

    public EntityManagerFactory getManagerFactory() {
        return managerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Integer addPerson(Person newPerson){
        entityManager.getTransaction().begin();
        Integer id =null;
        try{
            Person person = newPerson;
            person.setAccounts(new LinkedList<Account>());
            entityManager.persist(person);
            entityManager.getTransaction().commit();
            id = person.getId();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
        return id;
    }
    public void addAccount(String type, Integer idPerson){
        Person person = findPersonByID(idPerson);
        entityManager.getTransaction().begin();
        try{
            ExchangeRate exchangeRate = Converter.createExchangeRate(type);
                entityManager.persist(exchangeRate);
            Account account = new Account(type,0.);
            account.setRate(exchangeRate);
            account.setTransactions(new LinkedList<Transaction>());
            person.addAccount(account);
            entityManager.persist(account);
            entityManager.merge(person);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    private Person findPersonByID(Integer id){
        TypedQuery<Person> query = entityManager.createQuery("select p from Person p where p.id = :id", Person.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    public void replenishAnAccount(String type, Double total, Integer idPerson){
        Person person = findPersonByID(idPerson);
        Account account = findAccountByTypeAndPerson(type,person);
        Double balance = account.getBalance();
        entityManager.getTransaction().begin();
        try{
            Transaction transaction = new Transaction(total,new Date(),"replenish");
            balance = balance+total;
            account.setBalance(balance);
            account.addTransaction(transaction);
            entityManager.persist(transaction);
            entityManager.merge(account);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    private Account findAccountByTypeAndPerson(String type, Person person){
        TypedQuery<Account> query = entityManager.createQuery("select a from Account a where a.type = :t AND a.person = :person", Account.class);
        query.setParameter("t",type);
        query.setParameter("person",person);
        return query.getSingleResult();
    }

    public void makeTransfer(String typeFrom, String typeTo, String typeOfTotal,Double total, Integer idPerson){
        Person person = findPersonByID(idPerson);
        Account accountFrom = findAccountByTypeAndPerson(typeFrom,person);
        Account accountTo = findAccountByTypeAndPerson(typeTo,person);
        Double balanceFrom = accountFrom.getBalance();
        Double balanceTo = accountTo.getBalance();
        Double totalUAH = Converter.convertAmountToUAH(entityManager,typeOfTotal,total);
        entityManager.getTransaction().begin();
        try{
            Transaction tFrom = new Transaction(Converter.convertAmountToCurrency(entityManager,typeFrom,totalUAH),new Date(),"withdraw");
            Transaction tTo = new Transaction(Converter.convertAmountToCurrency(entityManager,typeTo,totalUAH),new Date(),"replenish");
            accountFrom.setBalance(withdraw(typeFrom,balanceFrom,totalUAH));
            accountTo.setBalance(replenish(typeTo,balanceTo,totalUAH));
            accountFrom.addTransaction(tFrom);
            accountTo.addTransaction(tTo);
            entityManager.merge(accountFrom);
            entityManager.merge(accountTo);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    private Double withdraw(String type, Double balance, Double total){
        Double balanceInUAH = Converter.convertAmountToUAH(entityManager,type,balance);
        Double decreesBalance = balanceInUAH-total;
        return Converter.convertAmountToCurrency(entityManager,type,decreesBalance);
    }
    private Double replenish(String type, Double balance, Double total){
        Double balanceInUAH = Converter.convertAmountToUAH(entityManager,type,balance);
        Double increase =balanceInUAH+total;
        return Converter.convertAmountToCurrency(entityManager,type,increase);
    }

    public void viewTotalAmount (Integer idPerson){
        Person person = findPersonByID(idPerson);
        List<Account> accounts = person.getAccounts();
        Double sum = 0.;
        for (Account account: accounts){
            String type = account.getType();
            Double balance = account.getBalance();
            sum+=Converter.convertAmountToUAH(entityManager,type,balance);
        }
        System.out.println("Total balance in UAH: " + sum);
    }

}
