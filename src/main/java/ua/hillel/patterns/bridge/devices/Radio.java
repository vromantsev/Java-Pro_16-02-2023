package ua.hillel.patterns.bridge.devices;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Radio implements Device {

    private static final Logger LOGGER = Logger.getLogger(Radio.class.getName());

    private boolean enabled;

    @Override
    public void enable() {
        LOGGER.log(Level.INFO, "Radio is ON!");
        enabled = true;
    }

    @Override
    public void disable() {
        LOGGER.log(Level.INFO, "Radio is OFF!");
        enabled = false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
