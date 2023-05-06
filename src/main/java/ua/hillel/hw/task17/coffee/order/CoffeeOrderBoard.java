package ua.hillel.hw.task17.coffee.order;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoffeeOrderBoard {

    private static final Logger LOGGER = Logger.getLogger(CoffeeOrderBoard.class.getName());

    private final OrderNumberGenerator orderNumberGenerator;
    private final Map<Integer, Order> orderNumberToOrderMap;

    public CoffeeOrderBoard() {
        this.orderNumberToOrderMap = new HashMap<>();
        this.orderNumberGenerator = new OrderNumberGenerator();
    }

    public void add(final Order order) {
        Objects.requireNonNull(order);
        var orderNumber = this.orderNumberGenerator.generateNextOrderNumber();
        order.setOrderNumber(orderNumber);
        orderNumberToOrderMap.put(orderNumber, order);
    }

    public void deliver() {
        this.orderNumberToOrderMap.entrySet().stream()
                .min(Map.Entry.comparingByKey())
                .ifPresent(e -> {
                    var orderNumber = e.getKey();
                    var order = e.getValue();
                    LOGGER.fine("Delivering an order %d for customer %s".formatted(orderNumber, order.getCustomerName()));
                    this.orderNumberToOrderMap.remove(orderNumber);
                });
    }

    public void deliver(final int orderNumber) {
        this.orderNumberToOrderMap.entrySet().stream()
                .filter(e -> e.getKey().compareTo(orderNumber) == 0)
                .findAny()
                .ifPresentOrElse(e -> {
                    LOGGER.fine("Order %d for customer %s is ready!".formatted(e.getKey(), e.getValue().getCustomerName()));
                    this.orderNumberToOrderMap.remove(e.getKey());
                }, () -> LOGGER.log(Level.SEVERE, "Order %d not found!".formatted(orderNumber)));
    }

    public String draw() {
        StringBuilder sb = new StringBuilder();
        sb.append("=====================\n");
        sb.append("Order number").append("    |   ").append("Customer name\n");
        this.orderNumberToOrderMap.forEach((orderNumber, order) -> {
            sb.append(orderNumber).append("               |   ").append(order.getCustomerName()).append("\n");
        });
        return sb.toString();
    }

    private static class OrderNumberGenerator {

        private int orderNumber;

        public int generateNextOrderNumber() {
            return this.orderNumber++;
        }
    }
}
