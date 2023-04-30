package ua.hillel.hw.task21;

import java.time.LocalDateTime;
import java.util.Objects;

public class Product {

    private Long id;
    private ProductType type;
    private double price;
    private boolean hasDiscount;
    private LocalDateTime createdAt;

    public Product() {
    }

    public Product(Long id, ProductType type, double price, boolean hasDiscount, LocalDateTime createdAt) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.hasDiscount = hasDiscount;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean hasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.price, price) != 0) return false;
        if (hasDiscount != product.hasDiscount) return false;
        if (!Objects.equals(id, product.id)) return false;
        if (!Objects.equals(type, product.type)) return false;
        return Objects.equals(createdAt, product.createdAt);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (hasDiscount ? 1 : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type=" + type +
                ", price=" + price +
                ", hasDiscount=" + hasDiscount +
                ", createdAt=" + createdAt +
                '}';
    }

    public enum ProductType {
        BOOK, FOOD, DRINKS, COMPUTER;
    }
}
