package com.tsvietkovich.restaurant;


import javax.persistence.*;

@Entity
@Table
//@NamedQuery(name = "Dish.findByWeight", query = "select d from Dish d where d.weight <= :w")
@PersistenceContext(unitName="JPAapps")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Double price;
    private Double weight;
    private Boolean sale;

    public Dish() {
    }

    public Dish(String name, Double price, Double weight, Boolean sale) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.sale = sale;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean getSale() {
        return sale;
    }

    public void setSale(Boolean sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Dish[" + "id= " + id + ", name= " + name + ", price= " + price + ", weight= " + weight + ", sale= " + sale + "]";
    }
}
