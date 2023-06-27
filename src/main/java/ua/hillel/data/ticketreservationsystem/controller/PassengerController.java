package ua.hillel.data.ticketreservationsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hillel.data.ticketreservationsystem.entity.Passenger;
import ua.hillel.data.ticketreservationsystem.service.PassengerService;

import java.util.UUID;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(final PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping
    public ResponseEntity<Passenger> create(@RequestBody final Passenger passenger) {
        var createdPassenger = this.passengerService.create(passenger);
        return ResponseEntity
                .ok()
                .header("X-Created-Passenger", UUID.randomUUID().toString())
                .body(createdPassenger);
    }

    @GetMapping
    public ResponseEntity<Passenger> getByFirstNameAndLastName(@RequestParam("firstName") final String firstName,
                                                               @RequestParam("lastName") final String lastName) {
        var passenger = this.passengerService.findByFirstNameAndLastName(firstName, lastName);
        return ResponseEntity
                .ok()
                .body(passenger);
    }

    @GetMapping("/{passengerId}")
    public ResponseEntity<Passenger> findById(@PathVariable("passengerId") final Long id) {
        var passenger = this.passengerService.findById(id);
        return ResponseEntity
                .ok()
                .body(passenger);
    }

}
