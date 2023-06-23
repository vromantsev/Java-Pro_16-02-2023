package ua.hillel.data.ticketreservationsystem.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ua.hillel.data.ticketreservationsystem.entity.Passenger;
import ua.hillel.data.ticketreservationsystem.entity.Ticket;

import java.util.Objects;

@Repository
public class SimplePassengerRepository implements PassengerRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Passenger> passengerRowMapper;

    public SimplePassengerRepository(final JdbcTemplate jdbcTemplate, final RowMapper<Passenger> passengerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.passengerRowMapper = passengerRowMapper;
    }

    @Override
    public void createTable(final String sql) {
        jdbcTemplate.execute(Objects.requireNonNull(sql));
    }

    @Override
    public Passenger create(final Passenger passenger) {
        Objects.requireNonNull(passenger);
        if (!Objects.isNull(passenger.getId())) {
            throw new IllegalArgumentException("Cannot create passenger with an id!");
        }
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("passenger")
                .usingGeneratedKeyColumns("id");
        Number id = insert.executeAndReturnKey(
                new MapSqlParameterSource()
                        .addValue("first_name", passenger.getFirstName())
                        .addValue("last_name", passenger.getLastName())
                        .addValue("email", passenger.getEmail())
        );
        passenger.setId(id.longValue());
        return passenger;
    }

    @Override
    public Passenger findByFirstNameAndLastName(final String firstName, final String lastName) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        var sql = """
                SELECT 
                    p.id, p.first_name, p.last_name, p.email, 
                    t.id, t.ticket_number, t.price, t.plane_number, t.seat, t.category, t.passenger_id
                FROM passenger p 
                INNER JOIN ticket t ON p.id = t.passenger_id
                WHERE first_name = ? AND last_name = ?
                """;
        return jdbcTemplate.queryForObject(sql, passengerRowMapper, firstName, lastName);
    }

    @Override
    public Passenger update(final Passenger passenger) {
        Objects.requireNonNull(passenger);
        Objects.requireNonNull(passenger.getId());
        var sql = "UPDATE passenger SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
        var rowsUpdated = jdbcTemplate.update(sql, passenger.getFirstName(), passenger.getLastName(), passenger.getEmail(), passenger.getId());
        if (rowsUpdated <= 0) {
            throw new RuntimeException("No rows were updated.");
        }
        return passenger;
    }

    @Override
    public boolean delete(final Long id) {
        Objects.requireNonNull(id);
        var sql = "DELETE FROM passenger WHERE id = ?";
        var rowsDeleted = jdbcTemplate.update(sql, id);
        return rowsDeleted >= 1;
    }

    @Override
    public Ticket createTicket(final Ticket ticket) {
        Objects.requireNonNull(ticket);
        Objects.requireNonNull(ticket.getPassenger());
        Objects.requireNonNull(ticket.getPassenger().getId());
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("ticket")
                .usingGeneratedKeyColumns("id");
        Number id = insert.executeAndReturnKey(
                new MapSqlParameterSource()
                        .addValue("ticket_number", ticket.getTicketNumber())
                        .addValue("price", ticket.getPrice())
                        .addValue("plane_number", ticket.getPlaneNumber())
                        .addValue("seat", ticket.getSeat())
                        .addValue("category", ticket.getCategory().name())
                        .addValue("passenger_id", ticket.getPassenger().getId())
        );
        ticket.setId(id.longValue());
        return ticket;
    }
}
