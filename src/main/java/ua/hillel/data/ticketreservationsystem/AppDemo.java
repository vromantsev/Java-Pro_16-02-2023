package ua.hillel.data.ticketreservationsystem;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.hillel.data.ticketreservationsystem.entity.Passenger;
import ua.hillel.data.ticketreservationsystem.entity.Ticket;
import ua.hillel.data.ticketreservationsystem.enums.Category;
import ua.hillel.data.ticketreservationsystem.repository.SimplePassengerRepository;

import java.math.BigDecimal;

public class AppDemo {

    private static final String CREATE_PASSENGER_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS passenger (
                id BIGSERIAL PRIMARY KEY,
                first_name VARCHAR(50) NOT NULL,
                last_name VARCHAR(50) NOT NULL,
                email VARCHAR(100) NOT NULL UNIQUE
            );
            """;
    private static final String CREATE_TICKET_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS ticket (
                id BIGSERIAL PRIMARY KEY,
                ticket_number VARCHAR(100) NOT NULL UNIQUE,
                price NUMERIC NOT NULL,
                plane_number VARCHAR(100) NOT NULL,
                seat NUMERIC NOT NULL,
                category VARCHAR(20) NOT NULL,
                passenger_id BIGINT NOT NULL,
                CONSTRAINT fk_passenger FOREIGN KEY(passenger_id) REFERENCES passenger(id)
            );
            """;

    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext("ua.hillel.data");

        var passengerRepository = ctx.getBean(SimplePassengerRepository.class);

        var passenger = new Passenger("Anton", "Bulgakov", "ab@gmail.com");
        passenger.setId(1L);
        /*Ticket ticket = new Ticket(
                "123FASFA", BigDecimal.valueOf(50.0), "AN-1", 12, Category.BUSINESS, passenger
        );

        System.out.println(passengerRepository.createTicket(ticket));*/

        System.out.println(passengerRepository.findByFirstNameAndLastName(passenger.getFirstName(), passenger.getLastName()));
    }
}
