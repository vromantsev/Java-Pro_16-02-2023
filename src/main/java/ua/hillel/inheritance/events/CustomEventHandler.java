package ua.hillel.inheritance.events;

public class CustomEventHandler extends AbstractEventService {

    public CustomEventHandler(EventStorage storage) {
        super(storage);
    }

    @Override
    public void pushToDownStreamSystem() {
        Event event = super.consume();
        if (event.isCustom()) {
            System.out.printf("Sending event %s to the custom downstream system%n", event);
        }
    }
}
