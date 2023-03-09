package ua.hillel.inheritance.events;

public class DefaultEventHandler extends AbstractEventService {

    public DefaultEventHandler(EventStorage storage) {
        super(storage);
    }

    @Override
    public void pushToDownStreamSystem() {
        Event event = super.consume();
        if (event.isDefault()) {
            System.out.printf("Sending event %s to default downstream system%n", event);
        }
    }
}
