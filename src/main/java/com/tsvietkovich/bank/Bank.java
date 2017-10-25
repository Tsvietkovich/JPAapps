package com.tsvietkovich.bank;


import java.util.Scanner;

public class Bank {
    private static Integer id;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try{
           Service service = new Service();
            try {
                String option = new String();
                while (!"exit".equals(option)){
                    System.out.println("1. Add person");
                    System.out.println("2. Add account");
                    System.out.println("3. Replenish An Account");
                    System.out.println("4. Make Transfer");
                    System.out.println("5. View Total Amount");
                    option = scanner.nextLine();
                    switch (option){
                        case "1":
                            id = addPerson(scanner,service);
                            break;
                        case "2":
                            addAccount(id,scanner,service);
                            break;
                        case "3":
                            replenishAccount(id,scanner,service);
                            break;
                        case "4":
                            transferMoney(id,scanner,service);
                            break;
                        case "5":
                            service.viewTotalAmount(id);
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
    public static Integer addPerson(Scanner scanner, Service service){
        Person person = new Person();
        person.setFirstName(Utils.readfirsName(scanner));
        person.setSecondName(Utils.readSecondName(scanner));
        person.setPassportSeries(Utils.readPassportSeries(scanner));
        person.setPassportNumber(Utils.readPassportNumber(scanner));
        person.setTelephone(Utils.readTelephone(scanner));
        person.setEmail(Utils.readEmail(scanner));
        return service.addPerson(person);
    }
    public static void addAccount(Integer id,Scanner scanner, Service service){
        String type = Utils.readTypeOfAccount(scanner);
        service.addAccount(type,id);
    }
    public static void replenishAccount(Integer id,Scanner scanner, Service service){
        String type = Utils.readTypeOfAccount(scanner);
        Double amount = Utils.readAmount(scanner);
        service.replenishAnAccount(type,amount,id);
    }
    public static void transferMoney(Integer id,Scanner scanner, Service service){
        String type1 = Utils.readTypeOfAccount(scanner);
        String type2 = Utils.readTypeOfAccount(scanner);
        Double amount = Utils.readAmount(scanner);
        String currency = Utils.readTypeOfCurrency(scanner);
        service.makeTransfer(type1,type2,currency,amount,id);
    }
}
