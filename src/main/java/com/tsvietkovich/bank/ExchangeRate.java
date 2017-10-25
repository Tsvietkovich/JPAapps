package com.tsvietkovich.bank;

import javax.persistence.*;

@Entity
@Table
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String type;
    private Integer units;
    private Double offCourse;

    public ExchangeRate() {
    }

    public ExchangeRate(String type, Integer units, Double offCourse) {
        this.type = type;
        this.units = units;
        this.offCourse = offCourse;
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


    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public Double getOffCourse() {
        return offCourse;
    }

    public void setOffCourse(Double offCourse) {
        this.offCourse = offCourse;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", units=" + units +
                ", offCourse=" + offCourse +
                '}';
    }
}
