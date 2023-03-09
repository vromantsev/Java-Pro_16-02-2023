package ua.hillel.inheritance.events;

public class Event {

    private EventType eventType;
    private String body;

    public Event(EventType eventType, String body) {
        this.eventType = eventType;
        this.body = body;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isDefault() {
        return this.eventType == EventType.DEFAULT;
    }

    public boolean isCustom() {
        return this.eventType == EventType.CUSTOM;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventType=" + eventType +
                ", body='" + body + '\'' +
                '}';
    }
}
