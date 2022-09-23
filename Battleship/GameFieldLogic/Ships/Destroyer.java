package Battleship.GameFieldLogic.Ships;

public class Destroyer extends Ship{
    protected boolean alive = true;
    protected int lives = 2;

    @Override
    public boolean isAlive() { return alive;    }

    @Override
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public int getLives() {
        return lives;
    }

    @Override
    public void setLives(int lives) {
        this.lives = lives;
    }
}
