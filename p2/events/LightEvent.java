package p2.events;

import p2.enums.Light;  // Import the Light enum from the enums package

public class LightEvent extends Event {
    private Light lightState;

    public LightEvent(String object, int time, Light lightState) {
        super(object, time);
        this.lightState = lightState;
    }

    public Light getLightState() {
        return lightState;
    }

    @Override
    public String toString() {
        return super.toString() + ", Light State=" + lightState;
    }
}
