package ua.hillel.patterns.bridge.remotes;

import ua.hillel.patterns.bridge.devices.Device;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicRemote implements Remote {

    private static final Logger LOGGER = Logger.getLogger(BasicRemote.class.getName());

    private Device device;

    public BasicRemote(Device device) {
        this.device = device;
    }

    @Override
    public void power() {
        LOGGER.log(Level.INFO, "Remote: button press.");
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }
}
