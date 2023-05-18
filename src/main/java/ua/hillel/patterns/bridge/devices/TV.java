package ua.hillel.patterns.bridge.devices;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TV implements Device {

    private static final Logger LOGGER = Logger.getLogger(TV.class.getName());

    private boolean enabled;

    @Override
    public void enable() {
        LOGGER.log(Level.INFO, "TV is ON!");
        enabled = true;
    }

    @Override
    public void disable() {
        LOGGER.log(Level.INFO, "TV is OFF!");
        enabled = false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
