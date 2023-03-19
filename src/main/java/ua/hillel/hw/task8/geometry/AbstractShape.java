package ua.hillel.hw.task8.geometry;

public class AbstractShape implements Shape {

    private double area;

    public AbstractShape(double area) {
        this.area = area;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    @Override
    public void setArea(double area) {
        this.area = area;
    }
}
