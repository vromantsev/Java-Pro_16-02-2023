package ua.hillel.hw.task8.geometry;

public final class ShapeUtils {

    private ShapeUtils() {}

    public static double sumOfSquares(Shape[] shapes) {
        double sum = 0;
        for (Shape shape : shapes) {
            sum += shape.getArea();
        }
        return sum;
    }
}
