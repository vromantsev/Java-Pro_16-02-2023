package ua.hillel.polymorphism;

public class FreeStyleDancer extends Dancer {

    public FreeStyleDancer(String name, int age) {
        super(name, age);
    }

    @Override
    public void dance() {
        System.out.println(this + "I'm a free style dancer.");
    }
}
