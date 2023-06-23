package ua.hillel.data.ticketreservationsystem.entity;

import ua.hillel.data.ticketreservationsystem.enums.Category;

import java.math.BigDecimal;
import java.util.Objects;

public class Ticket {

    private Long id;
    private String ticketNumber;
    private BigDecimal price;
    private String planeNumber;
    private int seat;
    private Category category;
    private Passenger passenger;

    public Ticket() {
    }

    public Ticket(String ticketNumber, BigDecimal price, String planeNumber, int seat, Category category, Passenger passenger) {
        this.ticketNumber = ticketNumber;
        this.price = price;
        this.planeNumber = planeNumber;
        this.seat = seat;
        this.category = category;
        this.passenger = passenger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPlaneNumber() {
        return planeNumber;
    }

    public void setPlaneNumber(String planeNumber) {
        this.planeNumber = planeNumber;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (seat != ticket.seat) return false;
        if (!Objects.equals(id, ticket.id)) return false;
        if (!Objects.equals(ticketNumber, ticket.ticketNumber))
            return false;
        if (!Objects.equals(price, ticket.price)) return false;
        if (!Objects.equals(planeNumber, ticket.planeNumber)) return false;
        if (category != ticket.category) return false;
        return Objects.equals(passenger, ticket.passenger);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ticketNumber != null ? ticketNumber.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (planeNumber != null ? planeNumber.hashCode() : 0);
        result = 31 * result + seat;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (passenger != null ? passenger.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", price=" + price +
                ", planeNumber='" + planeNumber + '\'' +
                ", seat=" + seat +
                ", category=" + category +
                '}';
    }
}
