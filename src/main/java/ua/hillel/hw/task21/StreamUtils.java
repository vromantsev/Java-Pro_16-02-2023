package ua.hillel.hw.task21;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public final class StreamUtils {

    private StreamUtils() {
    }

    /*
     * 1.2 Реалізувати метод отримання всіх продуктів у вигляді списку, категорія яких еквівалентна “Book” та ціна більш ніж 250.
     */
    public static List<Product> getProductsByTypeAndGreaterPrice(final List<Product> products, final Product.ProductType type, final double price) {
        return products.stream()
                .filter(product -> product.getType() == type && Double.compare(product.getPrice(), price) > 0)
                .toList();
    }

    /**
     * 2.2 Реалізувати метод отримання всіх продуктів як списку, категорія яких еквівалентна “Book” і з можливістю застосування знижки. Фінальний список повинен містити продукти з застосованою знижкою 10%.
     * Так, якщо Продукт A був з ціною 1.0 USD, то його фінальна ціна залишатиметься 0.9 USD
     */
    public static List<Product> getDiscountableProductsOfType(final List<Product> products,
                                                              final Product.ProductType type) {
        UnaryOperator<Product> setDiscountToProduct = product -> {
            product.setPrice(Double.sum(product.getPrice(), -(product.getPrice() / 10)));
            return product;
        };
        return products.stream()
                .filter(product -> product.getType() == type && product.hasDiscount())
                .map(setDiscountToProduct)
                .toList();
    }

    /**
     * 3.2 Реалізувати метод отримання найдешевшого продукту з категорії “Book”
     * 3.3 У випадку, якщо жоден продукт не знайдено (ситуація, коли немає продукту з категорією), викинути виняток з повідомленням “Продукт [категорія: ім'я_категорії] не знайдено”.
     */
    public static Product getCheapestProduct(final List<Product> products,
                                             final Product.ProductType type) {
        return products.stream()
                .filter(product -> product.getType() == type)
                .min(Comparator.comparing(Product::getPrice))
                .orElseThrow(() -> new NoSuchElementException("Product [category: %s] not found!".formatted(type)));
    }

    /**
     * 4.2 Реалізувати метод отримання трьох останніх доданих продуктів
     */
    public static List<Product> getThreeLastProducts(final List<Product> products) {
        return products.stream()
                .skip(products.size() - 3)
                .toList();
    }

    /**
     * 5.2 Реалізувати метод калькуляції загальної вартості продуктів, які відповідають наступним критеріям:
     * - продукт додано протягом поточного року
     * - продукт має тип “Book”
     * - ціна продукту не перевищує 75
     */
    public static List<Product> getCurrentYearProductsWithSpecificPrice(final List<Product> products,
                                                                        final Product.ProductType type,
                                                                        final double price) {
        return products.stream()
                .filter(product -> product.getCreatedAt().getYear() == LocalDateTime.now().getYear())
                .filter(product -> product.getType() == type && product.getPrice() <= price)
                .toList();
    }

    /**
     * 6.2 Реалізувати метод групування об'єктів за типом продукту. Таким чином результатом виконання методу
     * буде тип даних “Словник”, що зберігає пару ключ-значення: {тип: список_продуктів}
     */
    public static Map<Product.ProductType, List<Product>> getProductsGroupedByType(final List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getType));
    }
}
