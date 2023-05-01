package ua.hillel.hw.task18;

public class GenericFruitsDemo {
    public static void main(String[] args) {
        Fruit a1 = new Apple();
        Fruit a2 = new Apple();
        Fruit a3 = new Apple();
        Fruit a4 = new Apple();
        Fruit a5 = new Apple();
        Fruit o6 = new Orange();
        Fruit o7 = new Orange();
        Fruit o8 = new Orange();
        Fruit o9 = new Orange();
        Fruit o10 = new Orange();

        Box<Apple> apples = new Box<>(Apple.class);
        Box<Apple> apples2 = new Box<>(Apple.class);
        Box<Orange> oranges = new Box<>(Orange.class);

        apples.add((Apple) a1);
        apples.add((Apple) a2);
        apples.add((Apple) a3);

        apples2.add((Apple) a3);
        apples2.add((Apple) a4);
        apples2.add((Apple) a5);

        System.out.println(apples.getWeight());
        System.out.println(apples2.getWeight());

        System.out.println(apples.merge(apples2));
    }
}
