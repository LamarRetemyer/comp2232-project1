package p2.events;

public class OccupiedEvent extends Event {
    private boolean isOccupied;

    public OccupiedEvent(String object, int time, boolean isOccupied) {
        super(object, time);
        this.isOccupied = isOccupied;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    @Override
    public String toString() {
        return super.toString() + ", Occupied=" + isOccupied;
    }
}
