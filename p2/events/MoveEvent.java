package p2.events;

public class MoveEvent extends Event {
    private String fromLocation;
    private String toLocation;

    /**
     * MoveEvent is a method that creates the event with the specific parameters.
     * @param object The object related to the event.
     * @param time The time related to the event denoting the time it starts.
     * @param fromLocation The location where the object is moving from
     * @param toLocation The location where the object is moving to.
     * @return NONE
     */
    public MoveEvent(String object, int time, String fromLocation, String toLocation) {
        super(object, time);
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    /**
     * getFromLocation is a method that retrieves and returns the location where the object is moving from.
     * @param NONE
     * @return The location where the object is moving from
     */
    public String getFromLocation() {
        return fromLocation;
    }

    /**
     * getToLocation is a method that retrieves and returns the location that the object is moving to.
     * @param NONE
     * @return The location that the object is moving to.
     */
    public String getToLocation() {
        return toLocation;
    }

    /**
     * toString is a method that displays a string representation of the MoveEvent.
     * @param NONE
     * @return A string representation of the MoveEvent in detail.
     */
    @Override
    public String toString() {
        return super.toString() + ", From=" + fromLocation + ", To=" + toLocation;
    }
}
