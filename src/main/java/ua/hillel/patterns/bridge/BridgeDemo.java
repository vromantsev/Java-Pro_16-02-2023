package ua.hillel.patterns.bridge;

import ua.hillel.patterns.bridge.devices.Device;
import ua.hillel.patterns.bridge.devices.Radio;
import ua.hillel.patterns.bridge.devices.TV;
import ua.hillel.patterns.bridge.remotes.AdvancedRemote;
import ua.hillel.patterns.bridge.remotes.BasicRemote;
import ua.hillel.patterns.bridge.remotes.Remote;

public class BridgeDemo {
    public static void main(String[] args) {
        Device tv = new TV();
        Device radio = new Radio();

        Remote basicRemote = new BasicRemote(tv);
        Remote advancedRemote = new AdvancedRemote(radio);

        basicRemote.power();

        advancedRemote.power();
        ((AdvancedRemote) advancedRemote).mute();
    }
}
