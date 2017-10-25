package com.tsvietkovich.restaurant;


import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;
@PersistenceContext(unitName="JPAapps")

public class Service {
    private EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("JPAapps");
    private EntityManager entityManager = managerFactory.createEntityManager();
    private CriteriaBuilder builder = entityManager.getCriteriaBuilder();

    public EntityManagerFactory getManagerFactory() {
        return managerFactory;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void addDish(String name, Double price, Double weight, Boolean sale){
        entityManager.getTransaction().begin();
        try{
            Dish newDish = new Dish(name,price,weight,sale);
            entityManager.persist(newDish);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    public List<Dish> getDishesBetween (Double from, Double to){
        List<Dish> list = new LinkedList<Dish>();
        try {
            TypedQuery<Dish> query = entityManager.createQuery("select d from Dish d where d.price BETWEEN :min and :max", Dish.class);
            query.setParameter("min", from);
            query.setParameter("max", to);
            list = query.getResultList();
        }catch (NoResultException e){
            System.out.println("No dishes between this prices");
        }
       return list;
    }
    //OR
    public List<Dish> getDishesCriteriaBetween (Double from, Double to){
        CriteriaQuery<Dish> query = builder.createQuery(Dish.class);
        Root<Dish> root = query.from(Dish.class);
        Expression<Double> price = root.get("price");
        query.select(root).where(builder.between(price,from,to));
        TypedQuery<Dish> typedQuery = entityManager.createQuery(query);
        List<Dish> resultList = typedQuery.getResultList();
        return resultList;
    }
    public List<Dish> getSaleDishes(){
        List<Dish> list = new LinkedList<Dish>();
        try {
            TypedQuery<Dish> query = entityManager.createQuery("select d from Dish d where d.sale = true", Dish.class);
            list = query.getResultList();
        }catch (NoResultException e){
            System.out.println("No dishes with sale");
        }
        return list;
    }

    public List<Dish> getDishesUnderWeight(Double weight){
        List<Dish> resultList = new LinkedList<Dish>();
        try {
            TypedQuery<Dish> selectAll = entityManager.createQuery("select d from Dish d where d.weight <= :w", Dish.class);
            selectAll.setParameter("w",weight);
            List<Dish> dishes = selectAll.getResultList();
            int sum =0;
            for(Dish d: dishes){
                sum+=d.getWeight();
                if(sum<=weight){
                    resultList.add(d);
                }
            }
        }catch (NoResultException e){
            System.out.println("No dishes under this weigth");
        }

        return resultList;
    }


}
