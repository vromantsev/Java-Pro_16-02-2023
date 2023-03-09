package ua.hillel.inheritance.events;

public class EventsDemo {
    public static void main(String[] args) {
        EventStorage storage = new EventStorage(20);
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                storage.push(new Event(EventType.DEFAULT, "Default event " + i));
            } else {
                storage.push(new Event(EventType.CUSTOM, "Default event " + i));
            }
        }
        AbstractEventService eventService = new DefaultEventHandler(storage);
        eventService.pushToDownStreamSystem();
    }
}
