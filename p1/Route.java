package p1;

import java.util.*;

public class Route {
    private String name;
    private boolean isRoundTrip;
    private RSStatus status;
    private List<Segment> segments;
    private List<Station> stations;

    public Route(String name, boolean isRoundTrip) {
        this.name = name;
        this.isRoundTrip = isRoundTrip;
        this.status = RSStatus.CLOSED; // Initialize with closed status
        this.stations = new ArrayList<>();
        this.segments = new ArrayList<>();
    }

    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    public Station getStart() {
        if (!stations.isEmpty()) {
            return stations.get(0); // Return the first station in the list
        } else {
            return null; // Return null if the list is empty
        }
    }
    
    public Station getEnd() {
        if (!stations.isEmpty()) {
            return stations.get(stations.size() - 1); // Return the last station in the list
        } else {
            return null; // Return null if the list is empty
        }
    }
    
    public Station getNextStation(String station) {
        int index = getStationIndex(station);
        if (index != -1 && index + 1 < stations.size()) {
            return stations.get(index + 1); // Return the station at the next index
        } else {
            return null; // Return null if the station is not found or if it's the last station
        }
    }

    private int getStationIndex(String station) {
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).getName().equals(station)) {
                return i; // Return the index if the station is found
            }
        }
        return -1; // Return -1 if the station is not found
    }

    public String getPreviousStation(String stationName, boolean isAtStart) {
        for (int i = 0; i < stations.size(); i++) {
            Station station = stations.get(i);
            if (station.getName().equals(stationName)) {
                if (i == 0) {
                    // Handle the case when the station is at the start of the route
                    if (isAtStart) {
                        // Could return null or handle differently as per requirements
                        return null;
                    } else {
                        // Return the last station if it's a round trip and at the start
                        return isRoundTrip ? stations.get(stations.size() - 1).getName() : null;
                    }
                } else {
                    // Return the previous station's name
                    return stations.get(i - 1).getName();
                }
            }
        }
        return null; // Station not found in the route
    }
    

    public boolean canGetTo(String station) {
        int stationIndex = getStationIndex(station);
        if (stationIndex == -1) {
            // Station not found in the route
            return false;
        }
    
        // Check if the route can get to the specified station
        for (int i = 0; i < segments.size(); i++) {
            Segment segment = segments.get(i);
            if (i == segments.size() - 1 && segment.getEndStation().equals(station)) {
                // Last segment's end station matches the specified station
                return true;
            }
            if (segment.getStartStation().equals(station)) {
                // Specified station is the start station of a segment
                return true;
            }
        }
    
        return false; // Specified station is not reachable from the current route
    }
    

    public void addSegment(Segment segment) {
        segments.add(segment);
    }

    public void addSegments(LinkedHashSet<Segment> segments) {
        for (Segment segment : segments) {
            addSegment(segment);
        }
    }
    

    public void removeSegment(String segmentName) {
        for (int i = 0; i < segments.size(); i++) {
            Segment segment = segments.get(i);
            if (segment.getName().equals(segmentName)) {
                segments.remove(i); // Remove the segment at index i
                System.out.println("Segment " + segmentName + " removed from the route.");
                return;
            }
        }
        System.out.println("Segment " + segmentName + " not found in the route.");
    }
    

    public boolean containsSegment(String segmentName) {
        for (Segment segment : segments) {
            if (segment.getName().equals(segmentName)) {
                return true; // Found the segment
            }
        }
        return false; // Segment not found
    }
    

    public void changeLight(String startOfSegment) {
        // Find the segment based on the start station
        Segment targetSegment = null;
        for (Segment segment : segments) {
            if (segment.getStartStation().equals(startOfSegment)) {
                targetSegment = segment;
                break;
            }
        }
    
        // If the segment is found, change its light
        if (targetSegment != null) {
            targetSegment.changeLight();
        } else {
            System.out.println("Segment starting at " + startOfSegment + " not found.");
        }
    }

    public void addStation(Station station) {
        if (station != null && !stations.contains(station)) {
            stations.add(station);
        }
    }

    public boolean verify() {
        // A. Check if the route's name is valid
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        // B. Check if the segments list is non-empty
        if (segments == null || segments.isEmpty()) {
            return false;
        }

        // C & D. Check for round trip consistency
        Station startStation = segments.get(0).getStartStation();
        Station endStation = segments.get(segments.size() - 1).getEndStation();
        if (isRoundTrip && !startStation.equals(endStation)) {
            return false;
        }
        if (!isRoundTrip && startStation.equals(endStation)) {
            return false;
        }

        // E. Check if segments are properly sequenced
        for (int i = 0; i < segments.size() - 1; i++) {
            if (!segments.get(i).getEndStation().equals(segments.get(i + 1).getStartStation())) {
                return false;
            }
        }

        // F. Check for duplicate segments
        Set<Segment> uniqueSegments = new HashSet<>(segments);
        if (uniqueSegments.size() != segments.size()) {
            return false;
        }

        // G. Verify each segment
        for (Segment segment : segments) {
            if (!segment.verify()) {
                return false;
            }
        }

        // H. Check for loops in non-round trip routes
        if (!isRoundTrip) {
            Set<Station> visitedStations = new HashSet<>();
            for (Segment segment : segments) {
                if (!visitedStations.add(segment.getStartStation())) {
                    return false; // Loop detected
                }
            }
        }

        return true;
    }

    private ArrayList<Station> getStationList() {
        return new ArrayList<>(this.stations);
    }
    
    public void close() {
        status = RSStatus.CLOSED;
    }

    public void open() {
        status = RSStatus.OPEN;
    }

    public String getName() {
        return name;
    }

     public RSStatus getStatus() {
        return status;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setRoundTrip(boolean roundTrip) {
        isRoundTrip = roundTrip;
    }

    public void setStatus(RSStatus status) {
        this.status = status;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }
    // Other methods specific to Route can be added
}
