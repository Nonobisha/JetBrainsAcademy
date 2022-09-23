package Battleship.GameFieldLogic;

public interface GameFieldInterface {
    void addShip(String coords);
    void showField(boolean fogActive);
    boolean Run(int player);
}
