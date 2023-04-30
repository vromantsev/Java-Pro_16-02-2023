package ua.hillel.hw.task21;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StremsDemo {
    public static void main(String[] args) {
        var products = getProducts();
        System.out.println("Last 3 products: ");
        StreamUtils.getThreeLastProducts(products).forEach(System.out::println);
        System.out.println("The cheapest book: " + StreamUtils.getCheapestProduct(products, Product.ProductType.BOOK));
        System.out.println("Products of type with the price <= 75.00: ");
        StreamUtils.getCurrentYearProductsWithSpecificPrice(products, Product.ProductType.BOOK, 75.00).forEach(System.out::println);
        System.out.println("Discountable products of type BOOK: ");
        StreamUtils.getDiscountableProductsOfType(products, Product.ProductType.BOOK).forEach(System.out::println);
        System.out.println("Products by type and price: ");
        StreamUtils.getProductsByTypeAndGreaterPrice(products, Product.ProductType.BOOK, 250.00).forEach(System.out::println);
        System.out.println("Products grouped by type: ");
        StreamUtils.getProductsGroupedByType(products).forEach(((productType, products1) -> System.out.println(productType + ": " + products1)));
    }

    private static List<Product> getProducts() {
        return List.of(
                new Product(1L, Product.ProductType.COMPUTER, ThreadLocalRandom.current().nextDouble(300), true, LocalDateTime.now()),
                new Product(2L, Product.ProductType.DRINKS, ThreadLocalRandom.current().nextDouble(300), false, LocalDateTime.now()),
                new Product(3L, Product.ProductType.FOOD, ThreadLocalRandom.current().nextDouble(300), false, LocalDateTime.now()),
                new Product(4L, Product.ProductType.BOOK, ThreadLocalRandom.current().nextDouble(300), false, LocalDateTime.now()),
                new Product(5L, Product.ProductType.BOOK, ThreadLocalRandom.current().nextDouble(300), true, LocalDateTime.now())
        );
    }
}
