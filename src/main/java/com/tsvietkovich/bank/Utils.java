package com.tsvietkovich.bank;

import java.util.Scanner;

public class Utils {
    public static String readfirsName(Scanner scanner){
        System.out.println("Enter your name: ");
        return scanner.nextLine();
    }
    public static String readSecondName(Scanner scanner){
        System.out.println("Enter your surname: ");
        return scanner.nextLine();
    }
    public static String readPassportSeries(Scanner scanner){
        System.out.println("Enter passport series: ");
        return scanner.nextLine();
    }
    public static Integer readPassportNumber(Scanner scanner){
        System.out.println("Enter passport number: ");
        return Integer.parseInt(scanner.nextLine());
    }
    public static String readTelephone(Scanner scanner){
        System.out.println("Enter telephone: ");
        return scanner.nextLine();
    }
    public static String readEmail(Scanner scanner){
        System.out.println("Enter email: ");
        return scanner.nextLine();
    }
    public static String readTypeOfAccount(Scanner scanner){
        System.out.println("Enter type of account: ");
        return scanner.nextLine().toUpperCase();
    }
    public static Double readAmount(Scanner scanner){
        System.out.println("Enter amount: ");
        return Double.parseDouble(scanner.nextLine());
    }
    public static String readTypeOfCurrency(Scanner scanner){
        System.out.println("Enter type of currency: ");
        return scanner.nextLine().toUpperCase();
    }

}
