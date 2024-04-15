package p2.logging;

import java.util.ArrayList;
import p2.enums.*;
import p2.events.*;
import p2.interfaces.OpenClose;

public class Route extends Logable implements Comparable<Route>, OpenClose {
    private final String name;
    private final boolean isRoundTrip;
    private RSStatus status = RSStatus.OPEN;
    private ArrayList<Segment> segments = new ArrayList<>();

    public Route(String name, boolean isRoundTrip) {
        this.name = name != null ? name : "Unnamed";
        this.isRoundTrip = isRoundTrip;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isOpen() {
        return status == RSStatus.OPEN;
    }

    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    public Station getStart() {
        return segments.isEmpty() ? null : segments.get(0).getSegmentStart();
    }

    public Station getEnd() {
        return segments.isEmpty() ? null : segments.get(segments.size() - 1).getSegmentEnd();
    }

    public void addSegment(Segment segment) {
        if (segment != null && (segments.isEmpty() || segments.get(segments.size() - 1).getSegmentEnd().equals(segment.getSegmentStart()))) {
            segments.add(segment);
        } else {
            throw new IllegalArgumentException("Segment does not connect properly");
        }
    }

    public void removeSegment(String segmentName) {
        segments.removeIf(segment -> segment.getName().equals(segmentName));
    }

    public String getPreviousStation(String station, boolean isAtStart) {
        for (int i = 0; i < segments.size(); i++) {
            Segment seg = segments.get(i);
            if ((isAtStart && seg.getSegmentStart().getName().equals(station)) ||
                (!isAtStart && seg.getSegmentEnd().getName().equals(station))) {
                return i > 0 ? segments.get(i-1).getSegmentStart().getName() : null;
            }
        }
        return null;
    }

  
    public boolean verify() {
        if (name == null || name.isEmpty()) return false;
        if (segments.isEmpty()) return false;
        for (Segment seg : segments) {
            if (!seg.verify()) return false;
        }
        // Check continuity and no loop unless it's a round trip
        Station first = getStart();
        Station last = getEnd();
        if (isRoundTrip) {
            if (!first.equals(last)) return false;
        } else {
            if (first.equals(last)) return false;
        }
        return true;
    }

    @Override
    public Event close() {
        if (isOpen()) {
            status = RSStatus.CLOSED;
            // Assuming Action.CLOSE represents the closing action
            return new CFOSEvent("Route closed for maintenance: " + name, System.currentTimeMillis(), Action.Close);
        }
        return null; // Consider returning a specific event or null if no action is taken
    }

    @Override
    public Event open() {
        if (!isOpen()) {
            status = RSStatus.OPEN;
            // Assuming Action.OPEN represents the opening action
            return new CFOSEvent("Route opened: " + name, System.currentTimeMillis(), Action.Open);
        }
        return null; // Consider returning a specific event or null if no action is taken
    }

    @Override
    public String toString() {
        return "Route [name=" + name + ", isRoundTrip=" + isRoundTrip + ", status=" + status + ", segments="
                + (segments.isEmpty() ? "none" : segments) + ", verified=" + (verify() ? "Yes" : "No") + "]";
    }

    @Override
    public int compareTo(Route other) {
        return name.compareTo(other.getName());
    }
}
