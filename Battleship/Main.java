package Battleship;

import Battleship.GameFieldLogic.GameField;
import Battleship.GameFieldLogic.GameFieldInterface;

public class Main {

    public static void main(String[] args) {
        GameFieldInterface gameField = new GameField();
        GameFieldInterface gameField2 = new GameField();
        int counter = 0;
        int counter2 = 0;
        boolean a = true;
        boolean status;

        while(a) {
            status = false;
            if(counter>1) {
                counter = 0;
            }



            if (counter == 1) {
                if(counter2 == 1) {
                    status = gameField2.Run(counter);
                    counter2++;
                }
                else {
                    gameField.showField(true);
                    System.out.println("---------------------");
                    gameField2.showField(false);
                    status = gameField.Run(counter);
                }
            } else {
                if(counter2 == 0) {
                    status = gameField.Run(counter);
                    counter2++;
                } else {
                    gameField2.showField(true);
                    System.out.println("---------------------");
                    gameField.showField(false);
                    status = gameField2.Run(counter);
                }
            }

            if(status) {
                counter++;
            }
        }



    }
}
