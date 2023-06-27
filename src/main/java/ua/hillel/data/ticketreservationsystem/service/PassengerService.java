package ua.hillel.data.ticketreservationsystem.service;

import ua.hillel.data.ticketreservationsystem.entity.Passenger;

public interface PassengerService {

    Passenger create(final Passenger passenger);

    Passenger findByFirstNameAndLastName(final String firstName, final String lastName);

    Passenger findById(final Long id);

}
