package machine;

import machine.CoffeeTypes.Cappuccino;
import machine.CoffeeTypes.Espresso;
import machine.CoffeeTypes.Latte;

import java.util.Scanner;

public class CoffeeMachineImplementation {
    int water;
    int milk;
    int coffee;
    int cupsAvailable;
    int money;
    boolean Running;
    Scanner scanner;

    public CoffeeMachineImplementation(Scanner scanner) {
        this.water = 400;
        this.milk = 540;
        this.coffee = 120;
        this.cupsAvailable = 9;
        this.money = 550;
        this.scanner = scanner;
        this.Running = true;
    }

    public void setAction(String s) {
        switch(s){
            case("buy"):
                buyCoffee(scanner);
                break;
            case("fill"):
                fillMachine(scanner);
                break;
            case("take"):
                takeMoney();
                break;
            case("remaining"):
                getStats();
                break;
            case("exit"):
                Running = false;
                break;
            default:
                System.out.println("Error!");
        }
    }

    public boolean getStatus() {
        return Running;
    }

    private void fillMachine(Scanner scanner) {
        System.out.println("Write how many ml of water you want to add: ");
        int waterToAdd = (scanner.hasNextInt()) ? scanner.nextInt() : 0;
        System.out.println("Write how many ml of milk you want to add: ");
        int milkToAdd = (scanner.hasNextInt()) ? scanner.nextInt() : 0;
        System.out.println("Write how many grams of coffee beans you want to add: ");
        int coffeeToAdd = (scanner.hasNextInt()) ? scanner.nextInt() : 0;
        System.out.println("Write how many disposable cups you want to add: ");
        int cupsToAdd = (scanner.hasNextInt()) ? scanner.nextInt() : 0;

        water += waterToAdd;
        milk += milkToAdd;
        coffee += coffeeToAdd;
        cupsAvailable += cupsToAdd;
    }

    private void takeMoney() {
        System.out.printf("I gave you $%d", money);
        money = 0;
    }

    public void getStats() {
        System.out.printf("The coffee machine has: \n" +
                "%d ml of water \n" +
                "%d ml of milk \n" +
                "%d g of coffee beans \n" +
                "%d disposable cups \n" +
                "$%d of money \n", water, milk, coffee, cupsAvailable, money);
    }

    private void buyCoffee(Scanner scanner) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        int choice = (scanner.hasNextInt()) ? scanner.nextInt() : 0 ;

        switch(choice) {
            case(1):
                makeEspresso();
                break;
            case(2):
                makeLatte();
                break;
            case(3):
                makeCappuccino();
                break;
            default:
                System.out.println("Going back!");
                scanner.nextLine();
                break;
        }
    }

    private void makeEspresso() {
        Espresso espresso = new Espresso();
        String s = (espresso.makeCoffee(water, coffee, cupsAvailable)) ? changeStats(espresso.cupBought()) : "Not enough ingredients";
        System.out.println(s);
    }

    private String changeStats(int ... ingredients) {
       String status = "I have enough resources, making you a coffee!";
       if(ingredients.length == 4) {
           water -= ingredients[0];
           milk -= ingredients[1];
           coffee -= ingredients[2];
           money += ingredients[3];
           cupsAvailable--;
       } else if(ingredients.length == 3) {
           water -= ingredients[0];
           coffee -= ingredients[1];
           money += ingredients[2];
           cupsAvailable--;
       } else {
           status = "ERROR, stats not changed";
       }
       return status;
    }

    private void makeLatte() {
        Latte latte = new Latte();
        String s = (latte.makeCoffee(water, milk, coffee, cupsAvailable)) ? changeStats(latte.cupBought()) : "Not enough ingredients";
        System.out.println(s);
    }

    private void makeCappuccino() {
        Cappuccino cappuccino = new Cappuccino();
        String s = (cappuccino.makeCoffee(water, milk, coffee, cupsAvailable)) ? changeStats(cappuccino.cupBought()) : "Not enough ingredients";
        System.out.println(s);
    }

}
