package ua.hillel.reflection;

import ua.hillel.reflection.annotations.Inject;

public class Box {

    @Inject
    private Box box;
    private String message;
    private boolean isOpened;

    public Box() {
    }

    public Box(boolean isOpened) {
        this.isOpened = isOpened;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public void open() {
        System.out.println("Woohoo! The box is opened!");
    }

    @Override
    public String toString() {
        return "Box{" +
                "box=" + box +
                ", message='" + message + '\'' +
                ", isOpened=" + isOpened +
                '}';
    }
}
