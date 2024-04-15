package p2.events;

public class MoveEvent extends Event {
    private String fromLocation;
    private String toLocation;

    public MoveEvent(String object, int time, String fromLocation, String toLocation) {
        super(object, time);
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    @Override
    public String toString() {
        return super.toString() + ", From=" + fromLocation + ", To=" + toLocation;
    }
}
