package ua.hillel.data.ticketreservationsystem.exceptions;

public class CannotCreatePassengerWithIdException extends RuntimeException {

    public CannotCreatePassengerWithIdException(String message) {
        super(message);
    }

    public CannotCreatePassengerWithIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
