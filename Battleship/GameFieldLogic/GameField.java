package Battleship.GameFieldLogic;

import Battleship.GameFieldLogic.Coordinates.Coordinates;
import Battleship.GameFieldLogic.Ships.*;

import java.util.Arrays;
import java.util.Scanner;

//5 - Aircraft Carrier
//6 - Battleship
//7 - Submarine
//8 - Cruiser
//9 - Destroyer

public class GameField implements GameFieldInterface {

    private final int[][] field = new int[10][10];
    private int counter = 1;
    private final Scanner SCANNER = new Scanner(System.in);
    private String shipType = "";
    private int amountOfCells = 0;
    private boolean endOfSettingShips =  false;
    private boolean endOfGame = false;
    private boolean shotSuccessful = false;
    private int shipTypeNo = 0;
    private int numberOfShipsSank = 0;
    private boolean particularShipSank = false;
    private int step = 0; //0 - Setting ships, 1 - Shooting
    private int player = 0;

    AircraftCarrier aircraftCarrier = new AircraftCarrier();
    Battleship battleship = new Battleship();
    Cruiser cruiser = new Cruiser();
    Destroyer destroyer = new Destroyer();
    Submarine submarine = new Submarine();

    private final Coordinates coordinates = new Coordinates(field);

    public GameField() {
        initField();
    }

    private boolean shoot(String coordinate) {
        boolean result = false;
        boolean shipKilled;
        shotSuccessful = false;
        particularShipSank = false;
        if(coordinates.checkCoordinate(coordinate)) {
            if(field[coordinates.getRow()-1][coordinates.getColumn()-1] == 5
                    || field[coordinates.getRow()-1][coordinates.getColumn()-1] == 6
                    || field[coordinates.getRow()-1][coordinates.getColumn()-1] == 7
                    || field[coordinates.getRow()-1][coordinates.getColumn()-1] == 8
                    || field[coordinates.getRow()-1][coordinates.getColumn()-1] == 9) {
                shipKilled = shipController(field[coordinates.getRow()-1][coordinates.getColumn()-1]);
                if(shipKilled) {
                    particularShipSank = true;
                    numberOfShipsSank++;
                }
                field[coordinates.getRow()-1][coordinates.getColumn()-1] = 2;
                shotSuccessful = true;
            } else if(field[coordinates.getRow()-1][coordinates.getColumn()-1] == 2) {
            }
            else {
                field[coordinates.getRow()-1][coordinates.getColumn()-1] = 3;
            }
            result = true;
        }

        return result;
    }
    private void setPlayer(int player) {
        this.player = player+1;
    }

    public boolean Run(int player) {
        setPlayer(player);
        if (step == 0) {
            System.out.printf("Player %d, place your ships on the game field \n", this.player);
            showField(false);

            while (!endOfSettingShips) {
                setShips();
            }
            step++;

            System.out.println("Press enter and pass the move to another player");
            String s = (SCANNER.hasNextLine()) ? SCANNER.nextLine() : "";
        } else {


            System.out.printf("Player %d, it's your turn: \n", this.player);

            gameLive();

            System.out.println("Press enter and pass the move to another player");
            String s = (SCANNER.hasNextLine()) ? SCANNER.nextLine() : "";
        }
        return true;
    }

    private void gameLive() {
        boolean shooting = true;
        String s;
        while(shooting) {
            String coordinate = (SCANNER.hasNextLine()) ? SCANNER.nextLine() : "";
            if(shoot(coordinate)) {
                shooting = false;
            }
        }

        if(particularShipSank) {
            int AMOUNT_OF_SHIPS = 5;
            if(numberOfShipsSank == AMOUNT_OF_SHIPS) {
                s = "You sank the last ship. You won. Congratulations!";
                endOfGame = true;
            } else {
                s = "You sank a ship! Specify a new target: ";
            }
        }  else {
            s = (shotSuccessful) ? "You hit a ship! " : "You missed. ";
        }

        System.out.printf("%s \n", s);

    }

    private void setShips() {
        switch(counter) {
            case(1):
                shipType = "Aircraft Carrier ";
                amountOfCells = 5;
                coordinates.setAmountOfCells(amountOfCells);
                shipTypeNo = 5;
                break;
            case(2):
                shipType = "Battleship ";
                amountOfCells = 4;
                coordinates.setAmountOfCells(amountOfCells);
                shipTypeNo = 6;
                break;
            case(3):
                shipType = "Submarine ";
                amountOfCells = 3;
                coordinates.setAmountOfCells(amountOfCells);
                shipTypeNo = 7;
                break;
            case(4):
                shipType = "Cruiser ";
                amountOfCells = 3;
                coordinates.setAmountOfCells(amountOfCells);
                shipTypeNo = 8;
                break;
            case(5):
                shipType = "Destroyer ";
                amountOfCells = 2;
                coordinates.setAmountOfCells(amountOfCells);
                shipTypeNo = 9;
                break;
            default:
                endOfSettingShips = true;
                break;
        }
        if(!endOfSettingShips) {
            System.out.printf("Enter the coordinates of the %s (%d cells): \n", shipType, amountOfCells);
            String coords = (SCANNER.hasNextLine()) ? SCANNER.nextLine() : "";
            if(coordinates.checkCoordinates(coords, 2)) {
                addShip(coords);
                counter++;
            } else {
                System.out.printf("Error! Wrong length of the %s or position! Try again: \n", shipType);
            }
        } else {

        }
    }

    public void addShip(String coords) {

        switch(Coordinates.getPosition()){
            case VERTICALLY:
                for (int i = Coordinates.getSmallestRow()-1; i < amountOfCells+Coordinates.getSmallestRow()-1; i++) {
                    field[i][Coordinates.getColumnSecond()-1] = shipTypeNo;
                }
                break;
            case HORIZONTALLY:
                for (int i = Coordinates.getSmallestColumn()-1; i < amountOfCells+Coordinates.getSmallestColumn()-1; i++) {
                    field[Coordinates.getRowSecond()-1][i] = shipTypeNo;
                }
                break;
        }

        showField(false);
    }

    public void showField(boolean fogActive) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < field.length; i++) {
            System.out.printf("%c ", (char) (i+65));
            for (int j = 0; j < field[i].length; j++) {
                switch(field[i][j]){
                    case 0:
                        System.out.print("~ ");
                        break;
                    case 1:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        if(!fogActive) {
                            System.out.print("O ");
                        } else {
                            System.out.print("~ ");
                        }
                        break;
                    case 2:
                        System.out.print("X ");
                        break;
                    case 3:
                        System.out.print("M ");
                        break;
                    default:
                        System.out.println("ERROR!!!");
                        break;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean shipController(int shipNo) {
        boolean status = false;
            switch (shipNo) {
                case 5:
                    if (!aircraftCarrier.isAlive()) {
                        status = true;
                    } else {
                        aircraftCarrier.setLives(aircraftCarrier.getLives() - 1);
                    }

                    if (aircraftCarrier.getLives() == 0) {
                        aircraftCarrier.setAlive(false);
                    }

                    if (!aircraftCarrier.isAlive()) {
                        status = true;
                    }
                    break;
                case 6:
                    if (!battleship.isAlive()) {
                        status = true;
                    } else {
                        battleship.setLives(battleship.getLives() - 1);
                    }

                    if (battleship.getLives() == 0) {
                        battleship.setAlive(false);
                    }

                    if (!battleship.isAlive()) {
                        status = true;
                    }
                    break;
                case 7:
                    if (!submarine.isAlive()) {
                        status = true;
                    } else {
                        submarine.setLives(submarine.getLives() - 1);
                    }

                    if (submarine.getLives() == 0) {
                        submarine.setAlive(false);
                    }

                    if (!submarine.isAlive()) {
                        status = true;
                    }
                    break;
                case 8:
                    if (!cruiser.isAlive()) {
                        status = true;
                    } else {
                        cruiser.setLives(cruiser.getLives() - 1);
                    }

                    if (cruiser.getLives() == 0) {
                        cruiser.setAlive(false);
                    }

                    if (!cruiser.isAlive()) {
                        status = true;
                    }
                    break;
                case 9:
                    if (!destroyer.isAlive()) {
                        status = true;
                    } else {
                        destroyer.setLives(destroyer.getLives() - 1);
                    }

                    if (destroyer.getLives() == 0) {
                        destroyer.setAlive(false);
                    }

                    if (!destroyer.isAlive()) {
                        status = true;
                    }
                    break;
            }

        return status;
    }

    private void initField() {
        for (int[] booleans : field) {
            Arrays.fill(booleans, 0);
        }
    }
}