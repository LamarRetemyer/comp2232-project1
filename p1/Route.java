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

    /** isRoundTrip determines whether a trip is round. 
     * @param: NONE 
     * @return A type BOOLEAN of whether the trips is round or not.
     **/
    public boolean isRoundTrip() {
        return isRoundTrip;
    }
    
    /** getStart is a method that seeks to determine the first station in the list.
     * @param: NONE
     * @return An object station if there is one in the list, otherwise 'null' if the list is empty.
     **/
    public Station getStart() {
        if (!stations.isEmpty()) {
            return stations.get(0); 
        } else {
            return null; 
        }
    }
    
    /** getEnd is a method that seeks to determine the last station in the list.
     * @param: NONE
     * @return An object station if there is one in the list, otherwise 'null' if the list is empty.
     */
    public Station getEnd() {
        if (!stations.isEmpty()) {
            return stations.get(stations.size() - 1); 
        } else {
            return null; 
        }
    }
    
    /** getNextStation is a method that determines the next station in the list after the designated station.
     * @param station The string of the station object.
     * @return An object station after the designated station or null if the station isn't found or if it's the last station in the list.
     */
    public Station getNextStation(String station) {
        int index = getStationIndex(station);
        if (index != -1 && index + 1 < stations.size()) {
            return stations.get(index + 1); 
        } else {
            return null; 
        }
    }

    /**getStationIndex is a method that determines the index of the station based on its name.
     * @param station The string of the station object.
     * @return An integer that represents the index of the station in the list. Returns -1 if the station isn't found.
     */
    private int getStationIndex(String station) {
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).getName().equals(station)) {
                return i; 
            }
        }
        return -1; 
    }

    /** getPreviousStation is a method that determines the previous station's name based on a provided station and its position.
     * @param stationName The string of the station object.
     * @param isAtStart The boolean that determines whether a station is at the start of the route or not.
     * @return Returns the string of the station that is the previous station of the provided station, otherwise return null if the provided station is the first station in the list.
     */
    public String getPreviousStation(String stationName, boolean isAtStart) {
        for (int i = 0; i < stations.size(); i++) {
            Station station = stations.get(i);
            if (station.getName().equals(stationName)) {
                if (i == 0) {
                    if (isAtStart) {
                        return null;
                    } else {
                        return isRoundTrip ? stations.get(stations.size() - 1).getName() : null;
                    }
                } else {
                    return stations.get(i - 1).getName();
                }
            }
        }
        return null; 
    }
    

    /** canGetTo is a method that checks to see if a station is accessible in the route.
     * @param station The string of the station object.
     * @return A Boolean of false if it the station is not accesible and true if it is.
     */
    public boolean canGetTo(String station) {
        int stationIndex = getStationIndex(station);
        if (stationIndex == -1) {
            return false;
        }
    
        
        for (int i = 0; i < segments.size(); i++) {
            Segment segment = segments.get(i);
            if (i == segments.size() - 1 && segment.getEndStation().equals(station)) {
                return true;
            }
            if (segment.getStartStation().equals(station)) {
                return true;
            }
        }
    
        return false; 
    }
    

    /** addSegment is a method that adds an object segment to the route.
     * @param segment The segment object to be added to the route.
     * @return NONE
     */
    public void addSegment(Segment segment) {
        segments.add(segment);
    }

    /** addSegments is a method that adds multiple segment objects to the route.
     * @param segments The segemnt objects to be added to the route
     * @return NONE
     */
    public void addSegments(LinkedHashSet<Segment> segments) {
        for (Segment segment : segments) {
            addSegment(segment);
        }
    }
    

    /** removeSegment is a method that removes a segment from the route.
     * @param segmentName The string of the segmentName that should be removed.
     * @return: NONE
     */
    public void removeSegment(String segmentName) {
        for (int i = 0; i < segments.size(); i++) {
            Segment segment = segments.get(i);
            if (segment.getName().equals(segmentName)) {
                segments.remove(i); 
                System.out.println("Segment " + segmentName + " removed from the route.");
                return;
            }
        }
        System.out.println("Segment " + segmentName + " not found in the route.");
    }
    

    /** containsSegment is a method that checks a route to see if it contains a specific route.
     * @param segmentName The string of the segmentName to be checked.
     * @return Returns the boolean true if that specific name is found and false if it isn't.
     */
    public boolean containsSegment(String segmentName) {
        for (Segment segment : segments) {
            if (segment.getName().equals(segmentName)) {
                return true; 
            }
        }
        return false; 
    }
    

    /**changeLight is a method that changes the light of a segment.
     * @param startOfSegment The string of where the start of the segment begins.
     * @returns NONE
     */
    public void changeLight(String startOfSegment) {
        Segment targetSegment = null;
        for (Segment segment : segments) {
            if (segment.getStartStation().equals(startOfSegment)) {
                targetSegment = segment;
                break;
            }
        }
    
        
        if (targetSegment != null) {
            targetSegment.changeLight();
        } else {
            System.out.println("Segment starting at " + startOfSegment + " not found.");
        }
    }

    /** addStation is a method that adds a station to the route.
     * @param station The station object to be added to the route.
     * @return NONE
     */
    public void addStation(Station station) {
        if (station != null && !stations.contains(station)) {
            stations.add(station);
        }
    }

    /** verify is a validation method that seeks to ensure a number of things are consistent in the route. 
     * @return Returns boolean value depending on if any of the various checks are correct.
     */
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
    
    /** the method close refers to the status of the route as closed.
     * @param NONE
     * @return NONE
     */
    public void close() {
        status = RSStatus.CLOSED;
    }

    /** the methode open refers to the status of the route as open
     * @param NONE
     * @return NONE
     */
    public void open() {
        status = RSStatus.OPEN;
    }

    /** The method getName simply returns the name of the route
     * @param NONE
     * @return The string object of the name of the route.
     */
    public String getName() {
        return name;
    }
    
    /** The method getStatus returns the status of the route
     * @param NONE
     * @return The enum referring to the status of the route.
     */
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
   
}
