package p1;

public class Train {
    private int id;
    private int timeRegistered;
    private int startTime;
    private Station currentLocation;

    public Train(int id) {
        this.id = id;
        this.timeRegistered = 0;
        this.startTime = 0;
        this.currentLocation = null;
    }

    public boolean isRegistered() {
        return timeRegistered > 0;
    }

    public int whenRegistered() {
        return timeRegistered;
    }

    public void register(int time) {
        if (!isRegistered()) {
            this.timeRegistered = time;
        }
    }

    public void start() {
        if (isRegistered()) {
            this.startTime = this.timeRegistered;
        }
    }

    public void advance() {
        // Logic for moving the train to the next station
    }

    public String currentStation() {
        if (currentLocation != null) {
            return currentLocation.getName();
        } else {
            return "Unknown";
        }
    }

    public String nextStation() {
        // Logic for determining the next station based on route
        return "Next Station"; // Placeholder
    }

    public void changeRoute(Route route) {
        // Logic for changing the train's route
    }

    public boolean verify() {
        // Logic for verifying if the train's journey is valid
        return true; // Placeholder
    }

    // Getters and setters for the private fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeRegistered() {
        return timeRegistered;
    }

    public void setTimeRegistered(int timeRegistered) {
        this.timeRegistered = timeRegistered;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public Station getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Station currentLocation) {
        this.currentLocation = currentLocation;
    }
    
}

