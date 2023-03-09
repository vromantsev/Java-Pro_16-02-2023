package ua.hillel.polymorphism;

// BreakDanceDancer IS-A Dancer
// BreakDanceDancer IS-A Object
public class BreakDanceDancer extends Dancer {

    public BreakDanceDancer(String name, int age) {
        super(name, age);
    }

    @Override
    public void dance() {
        System.out.println(this + "I'm a break dance dancer.");
    }
}
