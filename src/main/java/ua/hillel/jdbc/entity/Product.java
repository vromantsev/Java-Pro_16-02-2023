package ua.hillel.jdbc.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Product implements Serializable {

    // MongoDB - string '92034uoiawye2qo43uooueauo'
    // UUID -> 128 '92034u-oiawye2q-o43uoou-eauo'
    private Long id;
    private String name;
    private double price;
    private LocalDateTime createdAt;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

        // product.price (pp), price (p) --> pp < p = -1; pp = p = 0; pp > p = 1
        if (Double.compare(product.price, price) != 0) return false;
        if (!Objects.equals(id, product.id)) return false;
        if (!Objects.equals(name, product.name)) return false;
        return Objects.equals(createdAt, product.createdAt);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }
}
