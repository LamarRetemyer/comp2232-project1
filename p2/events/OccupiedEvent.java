package p2.events;

public class OccupiedEvent extends Event {
    private boolean isOccupied;

    /**
     * OccupiedEvent is a method that creates an event with specific parameters.
     * @param object The object related to the event
     * @param time The time related to the event, denoting when the event starts.
     * @param isOccupied Boolean value determining whether or not the object is occupied.
     */
    public OccupiedEvent(String object, long time, boolean isOccupied) {
        super(object, time);
        this.isOccupied = isOccupied;
    }

    /**
     * isOccupied is a method that simply returns the boolean value determining whether the related object is occupied or not.
     * @param NONE
     * @return A boolean value of true if the object is occupied, false if not. 
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * toString is a method that provides a string represntation of the event.
     * @param NONE
     * @return A string representation of the OccupiedEvent in detail.
     */
    @Override
    public String toString() {
        return super.toString() + ", Occupied=" + isOccupied;
    }
}
