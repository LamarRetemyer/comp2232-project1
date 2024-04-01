package p1;
import java.util.*;


public class Route {
    private String name;
    private boolean isRoundTrip;
    private RSStatus status;
    private Station start;
    private Station end;
    private Set<Segment> segments;

    public Route(String name, boolean isRoundTrip, Station start, Station end) {
        this.name = name;
        this.isRoundTrip = isRoundTrip;
        this.status = RSStatus.OPEN;
        this.start = start;
        this.end = end;
        this.segments = new HashSet<>();
    }

    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    public Station getStart() {
        return start;
    }

    public Station getEnd() {
        return end;
    }

    public Station getNextStation(String station) {
        // Logic to find the next station based on the route
        return null; // Placeholder
    }

    public Station getPreviousStation(String station) {
        // Logic to find the previous station based on the route
        return null; // Placeholder
    }

    public boolean canGetTo(String station) {
        // Logic to check if the station can be reached from this route
        return true; // Placeholder
    }

    public void addSegment(Segment segment) {
        segments.add(segment);
    }

    public void addSegments(OrderedSet<Segment> segments) {
        // Logic to add multiple segments
    }

    public void removeSegment(String segment) {
        segments.removeIf(s -> s.getName().equals(segment));
    }

    public boolean containsSegment(String segment) {
        return segments.stream().anyMatch(s -> s.getName().equals(segment));
    }

    public void changeLight(String startOfSegment) {
        // Logic to change the traffic light at the start of the segment
    }

    public boolean verify() {
        // Logic to verify the route
        return true; // Placeholder
    }

    public void close() {
        status = RSStatus.CLOSED;
    }

    public void open() {
        status = RSStatus.OPEN;
    }

    // Getters and setters for private fields
}

enum RSStatus {
    OPEN,
    CLOSED
}



