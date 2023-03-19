package ua.hillel.hw.task8.geometry;

public class GeometryDemo {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(100.0), new Triangle(12.0), new Square(45.0)
        };
        System.out.println("Sum of squares: " + ShapeUtils.sumOfSquares(shapes));
    }
}
