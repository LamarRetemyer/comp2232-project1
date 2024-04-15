package p2.events;

import p2.enums.Light;  // Import the Light enum from the enums package

public class LightEvent extends Event {
    private Light lightState;

    /** LightEvent is a method that creates the even with specific parameters.
     * @param object The object related to the event.
     * @param time The time related to the even denoting when it occurs.
     * @param lightstate The object related to the state of the light in the event.
     * @return NONE
     */
    public LightEvent(String object, long time, Light lightState) {
        super(object, time);
        this.lightState = lightState;
    }

    /**
     * getLightState is a method that retrieves the light state related to the event.
     * @param NONE
     * @return the state of the light in the event.
     */
    public Light getLightState() {
        return lightState;
    }

    /**
     * toString is a method that returns a string representation of the light event.
     * @param NONE
     * @return A string representation of the LightEvent in detail. 
     */
    @Override
    public String toString() {
        return super.toString() + ", Light State=" + lightState;
    }
}
