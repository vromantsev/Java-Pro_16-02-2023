package ua.hillel.polymorphism;

public class Dancer {

    private String name;
    private int age;

    public Dancer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void dance() {
        System.out.println(this + "I'm a classic dancer.");
    }

    @Override
    public String toString() {
        return "My name is " + this.name + ", I'm " + this.age + " old. ";
    }
}
