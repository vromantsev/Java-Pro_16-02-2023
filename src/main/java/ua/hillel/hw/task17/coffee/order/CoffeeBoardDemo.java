package ua.hillel.hw.task17.coffee.order;

public class CoffeeBoardDemo {
    public static void main(String[] args) {
        CoffeeOrderBoard coffeeOrderBoard = new CoffeeOrderBoard();
        coffeeOrderBoard.add(new Order("Vlad"));
        coffeeOrderBoard.add(new Order("Stas"));
        coffeeOrderBoard.add(new Order("Mykyta"));
        coffeeOrderBoard.add(new Order("Egor"));
        System.out.println(coffeeOrderBoard.draw());
        coffeeOrderBoard.deliver();
        System.out.println(coffeeOrderBoard.draw());
        coffeeOrderBoard.deliver(4);
        System.out.println(coffeeOrderBoard.draw());
        coffeeOrderBoard.add(new Order("Seva"));
        System.out.println(coffeeOrderBoard.draw());
    }
}
