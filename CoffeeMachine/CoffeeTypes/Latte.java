package machine.CoffeeTypes;

public class Latte extends Espresso{
    private final int WATER = 350;
    private final int MILK = 75;
    private final int COFFEE = 20;
    private final int MONEY = 7;

    public boolean makeCoffee(int waterAvailable, int milkAvailable, int coffeeAvailable, int cupsAvailable) {
        if(WATER > waterAvailable
                || MILK > milkAvailable
                || COFFEE > coffeeAvailable
                || CUPS > cupsAvailable){
        } else {
            status = true;
        }
        return status;
    }

    @Override
    public int[] cupBought() {
        int[] ingredients = new int[4];
        ingredients[0] = WATER;
        ingredients[1] = MILK;
        ingredients[2] = COFFEE;
        ingredients[3] = MONEY;

        return ingredients;
    }
}
