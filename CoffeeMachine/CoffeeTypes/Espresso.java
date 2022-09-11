package machine.CoffeeTypes;

public class Espresso {
    private final int WATER = 250;
    private final int COFFEE = 16;
    private final int MONEY = 4;
    final int CUPS = 1;
    boolean status = false;

    public boolean makeCoffee(int waterAvailable, int coffeeAvailable, int cupsAvailable) {
        if(WATER > waterAvailable || COFFEE > coffeeAvailable || CUPS > cupsAvailable){
        } else {
            status = true;
        }
        return status;
    }

    public int[] cupBought() {
        int[] ingredients = new int[3];
        ingredients[0] = WATER;
        ingredients[1] = COFFEE;
        ingredients[2] = MONEY;

        return ingredients;
    }
}
