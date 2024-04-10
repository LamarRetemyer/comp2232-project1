package p1;

public class Train {
    private int id;
    private int timeRegistered;
    private int startTime;
    private Station currentLocation;
    private Route currentRoute; // Attribute to store current route
    private Segment currentSegment; // Attribute to store current segment

    public Train(int id) {
        this.id = id;
        this.timeRegistered = 0; // Initialize timeRegistered to 0
        this.startTime = 0; // Initialize startTime to 0
        this.currentLocation = null; // Default value
        this.currentRoute = null; // Initialize currentRoute to null
        this.currentSegment = null; // Initialize currentSegment to null
        // Initialize other attributes as needed
    }
    public boolean isRegistered() {
        return timeRegistered > 0;
    }
    

    public int whenRegistered() {
        return timeRegistered;
    }
    
    public void register(int time) {
        timeRegistered = time;
    }
    

    public void start() {
        // Implement logic to start the train
        // ...
    }

    public String currentStation() {
        if (currentLocation != null) {
            return currentLocation.getName();
        } else {
            return "Unknown"; // or handle this case based on your design
        }
    }
    

    public String nextStation() {
        if (currentRoute != null && currentSegment != null) {
            Station startStation = currentSegment.getStartStation();
            Station endStation = currentSegment.getEndStation();

            // Check if the current station is the start or end station of the segment
            if (currentLocation.equals(startStation)) {
                return endStation.getName(); // Next station is the end station of the segment
            } else if (currentLocation.equals(endStation)) {
                return startStation.getName(); // Next station is the start station of the segment
            } else {
                // Handle the case where the current location is not the start or end station
                // You may need additional logic based on your system's route and segment management
                return "Unknown"; // Placeholder value, adjust as needed
            }
        } else {
            return "Unknown"; // Placeholder value if route or segment is not set
        }
    }

    /** changeRoute is a method that allows a train to change its current route to another designated route.
     * @return NONE
     * @param r
     */
    public void changeRoute(Route r) {
        if (r != null) {
            this.currentRoute = r;
            System.out.println("Train " + id + " changed route to " + r.getName());
        } else {
            System.out.println("Invalid route specified.");
        }
    }
    
    /** getId is a method that simply returns the ID of a train.
     * @param NONE
     * @return Integer of the train ID
     */
    public int getId() {
        return id;
    }

    /** getTimeRegistered is a method that retrieves and returns the time a train is registered.
     * @param NONE
     * @return Integer of the time registered.
     */
    public int getTimeRegistered() {
        return timeRegistered;
    }

    /** getStartTime is a method that retrieves and returns the start time of the train's departure.
     * @param NONE
     * @return Integer of the time the train leaves.
     */
    public int getStartTime() {
        return startTime;
    }

    /** getCurrentLocation is a method that returns the current location of the train.
     * @param NONE
     * @return Current Location object showing where the train is.
     */
    public Station getCurrentLocation() {
        return currentLocation;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTimeRegistered(int timeRegistered) {
        this.timeRegistered = timeRegistered;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setCurrentLocation(Station currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**verify is a verification method that seeks to ensure a number of things are consistent in the train.
     * @param NONE
     * @return Boolean value of true if verification checks are accuruate/successful, false if not.
     */
    public boolean verify() {
        // Check if the time registered is greater than 0
        if (timeRegistered <= 0) {
            return false;
        }

        // Check if the route is not null and verified
        if (currentRoute == null || !currentRoute.verify()) {
            return false;
        }

        return true;
    }
    
}

