package Battleship.GameFieldLogic.Coordinates;

public interface CoordinatesInterface {

    boolean checkCoordinates(String coords, int length);
    void setAmountOfCells(int amountOfCells);
    boolean checkCoordinate(String coordinate);
    int getRow();
    int getColumn();

}
