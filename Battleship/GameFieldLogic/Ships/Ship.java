package Battleship.GameFieldLogic.Ships;

public abstract class Ship {

    public abstract boolean isAlive();

    public abstract void setAlive(boolean alive);

    public abstract int getLives();

    public abstract void setLives(int lives);
    }
