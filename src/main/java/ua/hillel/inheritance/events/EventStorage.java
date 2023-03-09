package ua.hillel.inheritance.events;

public class EventStorage {

    private static final int DEFAULT_SIZE = 100;

    private Event[] events;
    private int size;

    public EventStorage() {
        this(DEFAULT_SIZE);
    }

    public EventStorage(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size of the storage should be >= 1");
        }
        events = new Event[size];
    }

    public void push(Event event) {
        this.events[size++] = event;
    }

    public Event pop() {
        Event event = this.events[0];
        // null, <- 2,3,4,5 --> 2,3,4,5, null
        System.arraycopy(this.events, 1, this.events, 0, this.events.length - 1);
        return event;
    }
}
