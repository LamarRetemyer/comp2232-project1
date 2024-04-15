package p2.logging;

import p2.ts.TrafficLight;
import p2.enums.*;
import p2.events.*;
import p2.interfaces.OpenClose;

public class Segment extends Logable implements Comparable<Segment>, OpenClose {

    private final Station segmentStart;
    private final Station segmentEnd;
    private final String name;
    private RSStatus status = RSStatus.OPEN;
    private TrafficLight trafficLight;
    private String currentTrain = "null";

    public Segment(String name, Station segmentStart, Station segmentEnd) {
        this.name = name != null ? name.trim() : "Unnamed";
        this.trafficLight = new TrafficLight(); 
        this.segmentStart = segmentStart;
        this.segmentEnd = segmentEnd;
    }

    public String getName() {
        return name;
    }

    public Station getSegmentStart() {
        return segmentStart;
    }

    public Station getSegmentEnd() {
        return segmentEnd;
    }

    public boolean hasTrain() {
        return !"-1".equals(currentTrain);
    }

    @Override
    public boolean isOpen() {
        return status == RSStatus.OPEN;
    }

    public Event acceptTrain(String trainId) {
        this.currentTrain = trainId;
        return new OccupiedEvent("Segment", System.currentTimeMillis(), true);  // Indicating the segment is now occupied
    }

    public Event releaseTrain() {
        String trainId = this.currentTrain;
        this.currentTrain = null;
        return new OccupiedEvent("Segment", System.currentTimeMillis(), false);  // Indicating the segment is no longer occupied
    }

    public Event changeLight(long time) {
        // Toggle traffic light and capture the event
        Event lightChangeEvent = trafficLight.changeLight();
    
       
        System.out.println(lightChangeEvent);  
    
        // Optionally modify or extend the event information
        return new LightEvent("Segment Light Change at Time: " + time, time, trafficLight.getColour());
    }
    

    public Light lightColour() {
        return trafficLight.getColour();
    }


    public boolean verify() {
        boolean verified = name != null && !name.isEmpty() && segmentStart != null && segmentEnd != null;
        if (verified) {
            verified = !segmentStart.equals(segmentEnd);  // Ensure start and end are not the same
            verified = verified && trafficLight.verify(); // Traffic light must also be valid
        }
        return verified;
    }

    public Event close() {
        status = RSStatus.CLOSED;
        // Assuming Action.CLOSE represents the closing action
        return new CFOSEvent("Segment " + name + " closed for maintenance", System.currentTimeMillis(), Action.Close);
    }

    @Override
    public Event open() {
        status = RSStatus.OPEN;
        // Assuming Action.OPEN represents the opening action
        return new CFOSEvent("Segment " + name + " opened", System.currentTimeMillis(), Action.Open);
    }

    @Override
    public int compareTo(Segment other) {
        String thisStartEnd = segmentStart.getName() + segmentEnd.getName();
        String otherStartEnd = other.getSegmentStart().getName() + other.getSegmentEnd().getName();
        return thisStartEnd.compareTo(otherStartEnd);
    }

    @Override
    public String toString() {
        return "Segment [name=" + name + ", start=" + segmentStart.getName() +
               ", end=" + segmentEnd.getName() + ", status=" + status +
               ", trafficLight=" + trafficLight + ", train=" + (hasTrain() ? currentTrain : "none") +
               ", verified=" + verify() + "]";
    }
}
