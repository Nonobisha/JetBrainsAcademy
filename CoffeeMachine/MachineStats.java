package machine;

public class MachineStats {
    int water;
    int milk;
    int coffee;
    int cups;

    public MachineStats(int water, int milk, int coffee, int cups) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cups = cups;
    }

    public void check() {
       int cupsCanCreate = 0;
       boolean status = true;

       while(status){
           if(cupsCanCreate * 200 <= water
             && cupsCanCreate * 50 <= milk
             && cupsCanCreate * 15 <= coffee) {
               cupsCanCreate++;

           }
           else {
               status = false;
           }
       }

       cupsCanCreate -= 1;

       String toPrint = (cups > cupsCanCreate)
               ? String.format("No, I can make only %d cup(s) of coffee", cupsCanCreate)
               : (cups == cupsCanCreate)
                    ? "Yes, I can make that amount of coffee"
                    : String.format("Yes, I can make that amount of coffee (and even %d more than that)", cupsCanCreate - cups);

       System.out.println(toPrint);
    }

}
