package com.tsvietkovich.bank;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class Converter {

    public static ExchangeRate createExchangeRate(String type){
        ExchangeRate exchangeRate = new ExchangeRate();
        if(type.equals("USD")){
            exchangeRate.setType("USD");
            exchangeRate.setUnits(100);
            exchangeRate.setOffCourse(26.576754);
        }else if(type.equals("EUR")){
            exchangeRate.setType("EUR");
            exchangeRate.setUnits(100);
            exchangeRate.setOffCourse(31.201109);
        }else {
            exchangeRate.setType("UAH");
            exchangeRate.setUnits(100);
            exchangeRate.setOffCourse(1.);
        }
        return exchangeRate;
    }
    public static Double convertAmountToUAH (EntityManager manager,String typeFrom, Double amount){
        TypedQuery<ExchangeRate> query = manager.createQuery("select e from ExchangeRate e where e.type = :t", ExchangeRate.class);
        query.setParameter("t",typeFrom);
        ExchangeRate rate = query.getSingleResult();
        Double offCourse = rate.getOffCourse();
        return offCourse*amount;
    }
    public static Double convertAmountToCurrency (EntityManager manager,String typeTO, Double amount){
        TypedQuery<ExchangeRate> query = manager.createQuery("select e from ExchangeRate e where e.type = :t", ExchangeRate.class);
        query.setParameter("t",typeTO);
        ExchangeRate rate = query.getSingleResult();
        Double offCourse = rate.getOffCourse();
        return amount/offCourse;
    }
}
