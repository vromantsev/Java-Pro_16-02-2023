package ua.hillel.hw.task18;

public class Fruit {

    private float weight;

    public Fruit(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public <T extends Fruit> boolean isSameFruitType(final Class<T> fruitType) {
        return fruitType == this.getClass();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fruit fruit = (Fruit) o;

        return Float.compare(fruit.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "weight=" + weight +
                '}';
    }
}
