package com.tsvietkovich.restaurant;

import java.util.List;
import java.util.Scanner;

import static com.tsvietkovich.restaurant.Utils.*;

public class Restaurant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try{
            Service service = new Service();
            try {
                String option = new String();
                while (!"exit".equals(option)){
                    System.out.println("Choose options:");
                    System.out.println("1. Add dish");
                    System.out.println("2. Find dish between prices");
                    System.out.println("3 Pick dishes by weight");
                    option = scanner.nextLine();
                    switch (option){
                        case "1":
                            add(scanner,service);
                            break;
                        case "2":
                            find(scanner,service);
                            break;
                        case "3":
                            pick(scanner,service);
                            break;
                        default:
                            return;
                    }
                }
            }finally {
                service.getEntityManager().close();
                service.getManagerFactory().close();
                scanner.close();
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }
    public static void add(Scanner scanner, Service service){
        String name = readName(scanner);
        Double price = readPrice(scanner);
        Double weight = readWeight(scanner);
        Boolean sale = readSale(scanner);
        service.addDish(name,price,weight,sale);
    }
    public static void find(Scanner scanner, Service service){
        Double min = readPrice(scanner);
        Double max = readPrice(scanner);
        List<Dish> list = service.getDishesBetween(min,max);
        //List<Dish> list = service.getDishesCriteriaBetween(min,max);
        viewDishes(list);

    }
    public static void pick(Scanner scanner, Service service){
        Double weight = readWeight(scanner);
        List<Dish> list = service.getDishesUnderWeight(weight);
        viewDishes(list);
    }
}
