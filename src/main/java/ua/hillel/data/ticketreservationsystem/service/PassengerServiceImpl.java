package ua.hillel.data.ticketreservationsystem.service;

import org.springframework.stereotype.Service;
import ua.hillel.data.ticketreservationsystem.entity.Passenger;
import ua.hillel.data.ticketreservationsystem.exceptions.CannotCreatePassengerWithIdException;
import ua.hillel.data.ticketreservationsystem.repository.PassengerRepository;

import java.util.Objects;

@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerServiceImpl(final PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Override
    public Passenger create(final Passenger passenger) {
        Objects.requireNonNull(passenger);
        if (!Objects.isNull(passenger.getId())) {
            throw new CannotCreatePassengerWithIdException("Cannot create passenger with an id!");
        }
        return this.passengerRepository.create(passenger);
    }

    @Override
    public Passenger findByFirstNameAndLastName(final String firstName, final String lastName) {
        return this.passengerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Passenger findById(Long id) {
        return this.passengerRepository.findById(id);
    }
}
