package com.tsvietkovich.restaurant;

import java.util.List;
import java.util.Scanner;

public class Utils {
    public static String readName(Scanner scanner){
        System.out.println("Enter name of dish: ");
        return scanner.nextLine();
    }
    public static Double readWeight(Scanner scanner){
        System.out.println("Enter weight of dish: ");
        return Double.parseDouble(scanner.nextLine());
    }
    public static Double readPrice(Scanner scanner){
        System.out.println("Enter price of dish: ");
        return Double.parseDouble(scanner.nextLine());
    }
    public static Boolean readSale(Scanner scanner){
        System.out.println("Is dish in sale? ");
        return  Boolean.parseBoolean(scanner.nextLine());
    }

    public static void viewDishes(List<Dish> list){
        for(Dish d: list){
            System.out.println(d);
        }
    }
}
