package p1;
import java.util.*;


public class Route {
    private String name;
    private boolean isRoundTrip;
    private RSStatus status;
    // private Station start;
    // private Station end;
    private List<Segment> segments;

    public Route(String name, boolean isRoundTrip, List<Segment> segments) {
        this.name = name;
        this.isRoundTrip = isRoundTrip;
        this.status = RSStatus.OPEN;        
        this.segments = new ArrayList<Segment>();
    }

    // start = start;
    // end = end;

    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    public Station getStart() {
        return segments.get(0).getStart();
    }

    public void setStart(Station start){
        this.start = start;
    }

    public Station getEnd() {
        return segments.get(segments.size()-1).getSEND();
    }

    public void setEnd(Station end){
        this.end = end; 
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

    public void addSegments(Set<Segment> segments) {
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
    public String getName(){
        return name; 
    }

    public void setName(String name){
        this.name = name;
    }

    public RSStatus getStatus() {
        return status;
    }

    public void setStatus(RSStatus status) {
        this.status = status;
    }
}




