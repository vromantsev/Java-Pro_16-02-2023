package ua.hillel.data.ticketreservationsystem.repository;

import ua.hillel.data.ticketreservationsystem.entity.Passenger;
import ua.hillel.data.ticketreservationsystem.entity.Ticket;

public interface PassengerRepository {

    void createTable(final String sql);

    Passenger create(final Passenger passenger);

    Passenger findByFirstNameAndLastName(final String firstName, final String lastName);

    Passenger update(final Passenger passenger);

    boolean delete(final Long id);

    Ticket createTicket(final Ticket ticket);
}
