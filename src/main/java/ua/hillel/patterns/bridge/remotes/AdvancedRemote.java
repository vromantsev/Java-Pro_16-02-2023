package ua.hillel.patterns.bridge.remotes;

import ua.hillel.patterns.bridge.devices.Device;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AdvancedRemote extends BasicRemote {

    private static final Logger LOGGER = Logger.getLogger(AdvancedRemote.class.getName());

    public AdvancedRemote(Device device) {
        super(device);
    }

    public void mute() {
        LOGGER.log(Level.INFO, "AdvancedRemote: mute is called.");
    }
}
