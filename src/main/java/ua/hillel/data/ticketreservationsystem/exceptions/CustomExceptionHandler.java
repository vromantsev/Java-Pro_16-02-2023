package ua.hillel.data.ticketreservationsystem.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CannotCreatePassengerWithIdException.class)
    public ErrorResponse handleCannotCreatePassengerWIthIdException(final CannotCreatePassengerWithIdException exception) {
        LOGGER.error(exception.getMessage(), exception);
        return new ErrorResponse("TRS-1", "Invalid request payload", "[EXCEPTION HANDLER] Cannot create passenger with id.", HttpStatus.BAD_REQUEST.value());
    }

    // Ticket reservation system -> TRS-1 (CannotCreatePassengerWithIdException), TRS-2 (??) etc....
}
