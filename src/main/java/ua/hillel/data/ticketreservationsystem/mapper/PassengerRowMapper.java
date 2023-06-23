package ua.hillel.data.ticketreservationsystem.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.hillel.data.ticketreservationsystem.entity.Passenger;
import ua.hillel.data.ticketreservationsystem.entity.Ticket;
import ua.hillel.data.ticketreservationsystem.enums.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PassengerRowMapper implements RowMapper<Passenger> {

    @Override
    public Passenger mapRow(ResultSet rs, int rowNum) throws SQLException {
        Passenger passenger = new Passenger();
        passenger.setId(rs.getLong(1));
        passenger.setFirstName(rs.getString(2));
        passenger.setLastName(rs.getString(3));
        passenger.setEmail(rs.getString(4));
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong(5));
        ticket.setTicketNumber(rs.getString(6));
        ticket.setPrice(rs.getBigDecimal(7));
        ticket.setPlaneNumber(rs.getString(8));
        ticket.setSeat(rs.getInt(9));
        ticket.setCategory(Enum.valueOf(Category.class, rs.getString(10)));
        ticket.setPassenger(passenger);
        passenger.getTickets().add(ticket);
        return passenger;
    }
}
