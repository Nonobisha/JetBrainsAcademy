package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Stage 1/6
        //start();

        //Stage 2/6
        //coffeeCalculator(scanner);

        //Stage 3/6
        //checkIfCanMakeACoffee(scanner);

        //Stage 4/6
        //startFor4Stage(scanner);

        //Stage 5/6
        //startFor5Stage(scanner);

        //Stage 6/6 Same as 5/6
        startFor5Stage();
    }

    private static void startFor5Stage(Scanner scanner) {
        CoffeeMachineImplementation coffeeMachine = new CoffeeMachineImplementation(scanner);
        while(coffeeMachine.getStatus()) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String s = (scanner.hasNext()) ? scanner.nextLine() : "";
            coffeeMachine.setAction(s);
        }
    }

    private static void startFor4Stage(Scanner scanner) {
        CoffeeMachineImplementation coffeeMachine = new CoffeeMachineImplementation(scanner);
        coffeeMachine.getStats();
        System.out.println("Write action (buy, fill, take): ");
        String s = (scanner.hasNext()) ? scanner.next() : "";
        coffeeMachine.setAction(s);
        coffeeMachine.getStats();
    }

    private static void start() {
        System.out.println("Starting to make a coffee\n" +
                "Grinding coffee beans\n" +
                "Boiling water\n" +
                "Mixing boiled water with crushed coffee beans\n" +
                "Pouring coffee into the cup\n" +
                "Pouring some milk into the cup\n" +
                "Coffee is ready!");
    }

    private static void coffeeCalculator(Scanner scanner) {
        System.out.println("Write how many cups of coffee you will need? ");

        int cups = (scanner.hasNextInt()) ? scanner.nextInt() : 0;

        System.out.printf("For %d cups of coffee you will need: \n" +
                          "%d ml of water \n" +
                          "%d ml of milk \n" +
                          "%d g of coffee beans", cups, cups * 200, cups * 50, cups * 15);
    }

    private static void checkIfCanMakeACoffee(Scanner scanner) {
        System.out.println("Write how many ml of water the coffee machine has: ");

        int water = (scanner.hasNextInt()) ? scanner.nextInt() : 0;

        System.out.println("Write how many ml of milk the coffee machine has: ");

        int milk = (scanner.hasNextInt()) ? scanner.nextInt() : 0;

        System.out.println("Write how many grams of coffee beans the coffee machine has: ");

        int coffee = (scanner.hasNextInt()) ? scanner.nextInt() : 0;

        System.out.println("Write how many cups of coffee you will need: ");

        int cups = (scanner.hasNextInt()) ? scanner.nextInt() : 0;

        MachineStats coffeeMaker = new MachineStats(water, milk, coffee, cups);

        coffeeMaker.check();
    }

}
