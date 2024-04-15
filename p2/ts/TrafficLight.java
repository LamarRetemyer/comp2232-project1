package p2.ts;

import p2.enums.Light;
import p2.events.*;

public class TrafficLight {
    private static int nextID = 1; // Static counter to ensure each traffic light gets a unique ID
    private int id; // Unique identifier for each traffic light instance
    private Light colour; // Current color of the traffic light

    public TrafficLight() {
        this.id = nextID++;
        this.colour = Light.GREEN; // Default starting color
    }

    // Method to change the current light; returns an Event detailing the change
    public Event changeLight() {
        Light oldColour = this.colour;
        this.colour = (this.colour == Light.RED) ? Light.GREEN : Light.RED;
        return new LightEvent("TrafficLight ID " + id + " changed from " + oldColour + " to " + colour, System.currentTimeMillis(), colour);
    }

    // Getter for the current light colour
    public Light getColour() {
        return this.colour;
    }

    // Verifies the integrity of the traffic light's current state
    public boolean verify() {
        return this.colour != null && (this.colour == Light.RED || this.colour == Light.GREEN);
    }

    @Override
    public String toString() {
        return "TrafficLight [id=" + id + ", colour=" + colour + ", verified=" + verify() + "]";
    }
}
