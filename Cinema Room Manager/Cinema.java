import java.util.ArrayList;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //Stage 1/5
        //createCinema(7,8);

        //Stage 2/5
        //calculateTotalIncome();

        //Stage 3/5
        //ReserveASeat(calculateTotalIncome(scanner), scanner);

        //Stage 4/5
        //ReserveASeatUpgraded(scanner);

        //Stage 5/5
        ReserveASeatUpgradedWithStatistics(scanner);

    }

    private static void ReserveASeatUpgradedWithStatistics(Scanner scanner){

        int rowNumberToReserve = 0;
        int seatNumberToReserve = 0;
        int numberOfRows;
        int numberOfSeatsInEachRow;
        ArrayList<Integer> seatNumbersToReserve = new ArrayList<>();
        ArrayList<Integer> rowNumbersToReserve = new ArrayList<>();
        int ticketPrice;
        int option = 0;
        boolean applicationRunning = true;
        int amountOfFirstRows;
        int amountOfBackHalfRows;
        int checkInt = 0;
        int counter = 0;
        int totalIncome = 0;
        int currentIncome = 0;

        System.out.println("Enter the number of rows: ");
        numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        numberOfSeatsInEachRow = scanner.nextInt();
        amountOfFirstRows = numberOfRows/2;
        amountOfBackHalfRows = numberOfRows - amountOfFirstRows;
        int[][] seats = new int[numberOfRows][numberOfSeatsInEachRow];
        int[][] seatsReserved = new int[numberOfRows][numberOfSeatsInEachRow];

        while(applicationRunning) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");

            option = scanner.nextInt();

            switch (option) {
                case 1:

                    System.out.println("Cinema:");

                    for (int i = 0; i < seats.length; i++) {
                        if (i != 0) {
                            System.out.println();
                        }

                        for (int j = 0; j < seats[i].length + 1; j++) {

                            if (i == 0) {
                                int x = j + 1;
                                if (j == 0) {
                                    System.out.print("  ");
                                    System.out.print(x);
                                } else if (j == seats[i].length) {

                                } else {
                                    System.out.print(" ");
                                    System.out.print(x);
                                }
                            } else if (j == 0) {
                                System.out.print(i + " ");
                            } else {
                                if(!rowNumbersToReserve.isEmpty()) {
                                    checkInt = 0;
                                    for (int k = 0; k < rowNumbersToReserve.size(); k++) {
                                        if(i == rowNumbersToReserve.get(k) && j == seatNumbersToReserve.get(k)){
                                                System.out.print("B ");
                                                checkInt = 1;
                                                counter += 1;
                                        }
                                        else{
                                            if(counter == 0) {
                                                if (k == 0 && checkInt != 1) {
                                                    System.out.print("S ");
                                                }
                                            }
                                            else if( counter >= 1 && checkInt != 1){
                                                if(rowNumbersToReserve.size()>1) {
                                                    if ((k + 1) == rowNumbersToReserve.size()) {
                                                        System.out.print("S ");
                                                    }
                                                }
                                                else{
                                                    System.out.print("S ");
                                                }
                                            }
                                        }
                                    }
                                }else {
                                    System.out.print("S ");

                                }
                            }
                        }
                    }


                    System.out.println();
                    for (int i = 0; i < seats[0].length; i++) {
                        if (i == 0) {
                            System.out.print(seats.length + " ");
                        }
                        System.out.print("S ");
                    }
                    System.out.println();
                    break;
                case 2:
                    boolean ticketNotBought = true;

                    while (ticketNotBought) {
                        System.out.println("Enter a row number: ");
                        rowNumberToReserve = scanner.nextInt();
                        System.out.println("Enter a seat number in that row: ");
                        seatNumberToReserve = scanner.nextInt();
                        if (rowNumberToReserve <= numberOfRows && seatNumberToReserve <= numberOfSeatsInEachRow) {
                            if (seatsReserved[rowNumberToReserve - 1][seatNumberToReserve - 1] == 1) {
                                System.out.println("That ticket has already been purchased! \n");
                            } else {
                                if (numberOfRows * numberOfSeatsInEachRow >= 60) {
                                    if (rowNumberToReserve < amountOfBackHalfRows) {
                                        ticketPrice = 10;
                                        currentIncome += ticketPrice;
                                        ticketNotBought = false;
                                    } else {
                                        ticketPrice = 8;
                                        currentIncome += ticketPrice;
                                        ticketNotBought = false;
                                    }
                                } else {
                                    ticketPrice = 10;
                                    currentIncome += ticketPrice;
                                    ticketNotBought = false;
                                }
                                rowNumbersToReserve.add(rowNumberToReserve);
                                seatNumbersToReserve.add(seatNumberToReserve);
                                seatsReserved[rowNumberToReserve - 1][seatNumberToReserve - 1] = 1;

                                System.out.println("Ticket price: $" + ticketPrice);
                            }
                        } else{
                            System.out.println("Wrong input!");

                        }
                    }
                    break;
                case 3:
                    double counterOfPurchasedTickets = 0;
                    double numberOfSeats = numberOfRows * numberOfSeatsInEachRow;


                    for (int i = 0; i < seatsReserved.length; i++) {
                        for (int j = 0; j < seatsReserved[i].length; j++) {
                            if(seatsReserved[i][j] == 1) {
                                counterOfPurchasedTickets++;
                            }
                        }
                    }
                    double percentage = (counterOfPurchasedTickets/numberOfSeats) * 100;
                    percentage = Math.round(percentage * 100);
                    percentage = percentage / 100;


                    amountOfFirstRows = numberOfRows/2;
                    amountOfBackHalfRows = numberOfRows - amountOfFirstRows;

                    if(numberOfRows * numberOfSeatsInEachRow < 60) {
                        totalIncome = numberOfRows * numberOfSeatsInEachRow * 10;
                    }
                    else {
                        totalIncome = amountOfFirstRows * numberOfSeatsInEachRow * 10
                                + amountOfBackHalfRows * numberOfSeatsInEachRow * 8;
                    }

                    System.out.printf("Number of purchased tickets: %d \n", (int) counterOfPurchasedTickets);
                    System.out.printf("Percentage: %,.2f%% %n", percentage);
                    System.out.println("Current income: $" + currentIncome);
                    System.out.println("Total income: $" + totalIncome);

                    break;
                default:
                    applicationRunning = false;
                    break;
            }
        }
    }

    private static void ReserveASeatUpgraded(Scanner scanner){

        int rowNumberToReserve = 0;
        int seatNumberToReserve = 0;
        int numberOfRows;
        int numberOfSeatsInEachRow;
        ArrayList<Integer> seatNumbersToReserve = new ArrayList<>();
        ArrayList<Integer> rowNumbersToReserve = new ArrayList<>();
        int ticketPrice;
        int option = 0;
        boolean applicationRunning = true;
        int amountOfFirstRows;
        int amountOfBackHalfRows;
        int checkInt = 0;
        int counter = 0;

        System.out.println("Enter the number of rows: ");
        numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        numberOfSeatsInEachRow = scanner.nextInt();
        amountOfFirstRows = numberOfRows/2;
        amountOfBackHalfRows = numberOfRows - amountOfFirstRows;


        while(applicationRunning) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "0. Exit");

            option = scanner.nextInt();
            switch (option) {
                case 1:
                    int[][] seats = new int[numberOfRows][numberOfSeatsInEachRow];
                    System.out.println("Cinema:");


                            for (int i = 0; i < seats.length; i++) {
                                if (i != 0) {
                                    System.out.println();
                                }

                                for (int j = 0; j < seats[i].length + 1; j++) {

                                        if (i == 0) {
                                            int x = j + 1;
                                            if (j == 0) {
                                                System.out.print("  ");
                                                System.out.print(x);
                                            } else if (j == seats[i].length) {

                                            } else {
                                                System.out.print(" ");
                                                System.out.print(x);
                                            }
                                        } else if (j == 0) {
                                            System.out.print(i + " ");
                                        } else {
                                            if(!rowNumbersToReserve.isEmpty()) {
                                                checkInt = 0;
                                                for (int k = 0; k < rowNumbersToReserve.size(); k++) {
                                                    if(i == rowNumbersToReserve.get(k) && j == seatNumbersToReserve.get(k)){
                                                        System.out.print("B ");
                                                        checkInt = 1;
                                                        counter+=1;
                                                    }
                                                    else{
                                                        if(counter == 0) {
                                                            if (k == 0 && checkInt != 1) {
                                                                System.out.print("S ");
                                                            }
                                                        }
                                                        else if( counter >= 1 && checkInt != 1){
                                                            if(rowNumbersToReserve.size()>1) {
                                                                if ((k + 1) == rowNumbersToReserve.size()) {
                                                                    System.out.print("S ");
                                                                }
                                                            }
                                                            else{
                                                                System.out.print("S ");
                                                            }
                                                        }
                                                    }
                                                }
                                            }else {
                                                System.out.print("S ");

                                            }
                                    }
                                }
                            }


                    System.out.println();
                    for (int i = 0; i < seats[0].length; i++) {
                        if (i == 0) {
                            System.out.print(seats.length + " ");
                        }
                        System.out.print("S ");
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Enter a row number: ");
                    rowNumberToReserve = scanner.nextInt();
                    rowNumbersToReserve.add(rowNumberToReserve);
                    System.out.println("Enter a seat number in that row: ");
                    seatNumberToReserve = scanner.nextInt();
                    seatNumbersToReserve.add(seatNumberToReserve);
                    if(numberOfRows * numberOfSeatsInEachRow >= 60) {
                        if (rowNumberToReserve < amountOfBackHalfRows) {
                            ticketPrice = 10;
                        } else {
                            ticketPrice = 8;
                        }
                    }
                    else{
                        ticketPrice = 10;
                    }
                    System.out.println("Ticket price: $" + ticketPrice);
                    break;
                default:
                    applicationRunning = false;
                    break;
            }
        }
    }

    private static void ReserveASeat(ArrayList<Integer> list, Scanner scanner){
        createCinema(list.get(0), list.get(1));
        int rowNumberToReserve;
        int seatNumberToReserve;
        int ticketPrice;

        System.out.println("Enter a row number: ");
        rowNumberToReserve = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        seatNumberToReserve = scanner.nextInt();
        if(list.get(0) * list.get(1) >= 60) {
            if (rowNumberToReserve < list.get(4)) {
                ticketPrice = 10;
            } else {
                ticketPrice = 8;
            }
        }
        else{
            ticketPrice = 10;
        }
        System.out.println("Ticket price: $" + ticketPrice);

        int[][] seats = new int[list.get(0)][list.get(1)];
        System.out.println("Cinema:");
        for (int i = 0; i < seats.length; i++) {
            if(i != 0) {
                System.out.println();
            }
            for (int j = 0; j < seats[i].length+1; j++) {

                if(i == 0) {
                    int x = j + 1;
                    if(j == 0) {
                        System.out.print("  ");
                        System.out.print(x);
                    }
                    else if(j == seats[i].length){

                    }
                    else {
                        System.out.print(" ");
                        System.out.print(x);
                    }
                }
                else if (j == 0){
                    System.out.print(i+ " ");
                }
                else if(j == seatNumberToReserve && i == rowNumberToReserve) {
                    System.out.print("B ");
                }
                else {
                    System.out.print("S ");
                }
            }
        }
        System.out.println();
        for (int i = 0; i < seats[0].length; i++) {
            if(i == 0) {
                System.out.print(seats.length+ " ");
            }
            System.out.print("S ");
        }
        System.out.println();
    }

    private static ArrayList<Integer> calculateTotalIncome(Scanner scanner) {
        int totalIncome = 0;
        ArrayList<Integer> listToReturn = new ArrayList<>();
        int amountOfFirstRows = 0;
        int amountOfBackHalfRows = 0;

        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int numberOfSeatsInRow = scanner.nextInt();

        amountOfFirstRows = numberOfRows/2;
        amountOfBackHalfRows = numberOfRows - amountOfFirstRows;

        if(numberOfRows * numberOfSeatsInRow < 60) {
            totalIncome = numberOfRows * numberOfSeatsInRow * 10;
        }
        else {
            totalIncome = amountOfFirstRows * numberOfSeatsInRow * 10
                    + amountOfBackHalfRows * numberOfSeatsInRow * 8;
        }
        System.out.println("Total income: \n$" + totalIncome);
        listToReturn.add(numberOfRows);
        listToReturn.add(numberOfSeatsInRow);
        listToReturn.add(totalIncome);
        listToReturn.add(amountOfFirstRows);
        listToReturn.add(amountOfBackHalfRows);

        return listToReturn;
    }

    private static void createCinema(int rows, int numberOfSeats){
        int[][] seats = new int[rows][numberOfSeats];
        System.out.println("Cinema:");
        for (int i = 0; i < seats.length; i++) {
            if(i != 0) {
                System.out.println();
            }
            for (int j = 0; j < seats[i].length+1; j++) {

                if(i == 0) {
                    int x = j + 1;
                    if(j == 0) {
                        System.out.print("  ");
                        System.out.print(x);
                    }
                    else if(j == seats[i].length){

                    }
                    else {
                        System.out.print(" ");
                        System.out.print(x);
                    }
                }
                else if (j == 0){
                    System.out.print(i+ " ");
                }
                else {
                    System.out.print("S ");
                }
            }
        }
        System.out.println();
        for (int i = 0; i < seats[0].length; i++) {
            if(i == 0) {
                System.out.print(seats.length+ " ");
            }
            System.out.print("S ");
        }
        System.out.println();
    }
}
