package ua.hillel.inheritance.events;

public interface EventService {

    void send(Event event);

    Event consume();

    void pushToDownStreamSystem();

}
