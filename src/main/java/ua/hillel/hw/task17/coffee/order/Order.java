package ua.hillel.hw.task17.coffee.order;

import java.util.Objects;

public class Order {

    private int orderNumber;
    private String customerName;

    public Order(String customerName) {
        this.customerName = customerName;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderNumber != order.orderNumber) return false;
        return Objects.equals(customerName, order.customerName);
    }

    @Override
    public int hashCode() {
        int result = orderNumber;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
