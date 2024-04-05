package p1;
import java.util.*;


public class Route {
    private String name;
    private boolean isRoundTrip;
    private RSStatus status;
    private List<Segment> segments;
    private List<Station> stations;

    public Route(String name, boolean isRoundTrip, List<Station> stations) {
        this.name = name;
        this.isRoundTrip = isRoundTrip;
        this.status = RSStatus.OPEN;
            
        this.stations = new ArrayList<Station>();
    }

   

    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    public Station getStart() {
        return stations.get(0);
    }
 

    public Station getEnd() {
        return stations.get(stations.size()-1);
    }
  

    public Station getNextStation(String station) {
        // Find the index of the given station in the stations list
        int index = -1;
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).getName().equals(station)) {
                index = i;
                break;
            }
        }
    
        // If the station was found and it's not the last station, return the next station
        if (index != -1 && index < stations.size() - 1) {
            return stations.get(index + 1);
        } else {
            return null; // No next station or station not found
        }
    }
    

    public Station getPreviousStation(String station) {
    // Find the index of the given station in the stations list
    int index = -1;
    for (int i = 0; i < stations.size(); i++) {
        if (stations.get(i).getName().equals(station)) {
            index = i;
            break;
        }
    }

    // If the station was found and it's not the first station, return the previous station
    if (index > 0 && index < stations.size()) {
        return stations.get(index - 1);
    } else {
        return null; // No previous station or station not found
    }
}

      

    public boolean canGetTo(String station) {        
        for (Station s : stations) {
            if (s.getName().equals(station)) {
                return true; // Station can be reached
            }
        }
        return false; // Station cannot be reached
    }   
    

    public void addSegment(Segment segment) {
        segments.add(segment);
    }

    public void addSegments(Set<Segment> segments) {
        this.segments.addAll(segments);       
    }

    public void removeSegment(String segment) {
        segments.removeIf(s -> s.getName().equals(segment));
    }

    public boolean containsSegment(String segment) {
        return segments.stream().anyMatch(s -> s.getName().equals(segment));
    }

    public void changeLight(String startOfSegment) {
        // Find the segment with the given startOfSegment name
        Segment targetSegment = segments.stream().filter(s -> s.getName().equals(startOfSegment)).findFirst().orElse(null);    
        if (targetSegment != null) {
            // Change the traffic light of the target segment
            targetSegment.changeLight();
        } else {
            System.out.println("Segment not found.");
        }
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




