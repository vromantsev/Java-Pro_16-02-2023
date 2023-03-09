package ua.hillel.inheritance.events;

import java.util.Objects;

public abstract class AbstractEventService implements EventService {

    private EventStorage storage;

    public AbstractEventService(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void send(Event event) {
        Objects.requireNonNull(event);
        this.storage.push(event);
    }

    @Override
    public Event consume() {
        return this.storage.pop();
    }

    public abstract void pushToDownStreamSystem();

}
