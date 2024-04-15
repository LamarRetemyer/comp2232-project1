package p1;

public class Segment {
    private String name;
    private RSStatus status;
    private Train train;
    private TrafficLight trafficLight;
    private boolean isOccupied;

    public Segment(String name) {
        this.name = name;
        this.status = RSStatus.CLOSED; // Initialize with closed status
        this.train = null; // Initialize with no train
        this.trafficLight = new TrafficLight(0); 
        this.isOccupied = false;
    }

    /** hasTrain is a method that checks whether there is a train in the segment.
     * @param NONE
     * @return Boolean value of true if there is a train on the segment and false if not.
     */
    public boolean hasTrain() {
        return train != null;
    }

    /** isOpen is a method that checks whether the segment is open.
     * @param NONE
     * @return Boolean value of true if segment is open and false if closed.
     */
    public boolean isOpen() {
        return status == RSStatus.OPEN;
    }

    /** acceptTrain is a method that checks to see if the segment can accept a train based on its current status and whether it is currently containing a train.
     * @param train Train object to be accepted.
     * @return Boolean value of true if the train can successfully be accepted, false if not.
     */
    public boolean acceptTrain(Train train) {
        if (isOpen() && !hasTrain()) {
            this.train = train;
            return true;
        }
        return false;
    }

    /** releaseTrain is a method that releases or removes a train within the current segment.
     * @param NONE
     * @return NONE
     */
    public void releaseTrain() {
        if (hasTrain()) {
            this.train = null;
        }
    }

    /** changeLight is a method that changes the color of the light based on its occupation status.
     * @param NONE
     * @return NONE
     */
    public void changeLight() {
        if (this.isOccupied) {
            this.trafficLight.setColour(Light.RED);
        } else {
            this.trafficLight.setColour(Light.GREEN);
        }
    }

    
    /** setOccupied is a method that updates its occupancy status and light accordingly.
     * @return NONE
     * @param occupied Boolean value of true if it is occupied and false if it is not.
     */
    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
        changeLight();
    }


    /** lightColour is a method that checks whether the light colour is green.
     * @param NONE
     * @return Boolean value of true if the traffic light is green and false if it is not.
     */
    public boolean lightColour() {
        return trafficLight.getColour() == Light.GREEN;
    }

    /** getTrafficLight is a method that simply retrieves and displays the traffic light at the given segment.
     * @param NONE
     * @return TrafficLight object of the designated segment.
     */
    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    /** getStartStation is a method that retrieves the starting station of the segment.
     * @param NONE
     * @return The Station object at the start of the segment.
     */
    public Station getStartStation() {
        // Assuming the segment name format is "StartStation->EndStation"
        String[] stations = name.split("->");
        if (stations.length >= 2) {
            return new Station(stations[0]); // Assuming Station constructor takes station name
        } else {
            return null; // Handle invalid segment name format
        }
    }

    /** getEndStation is a method that retrieves and displays the station at the end of the segment.
     * @param NONE
     * @return The Station object at the end of the segment.
     */
    public Station getEndStation() {
        // Assuming the segment name format is "StartStation->EndStation"
        String[] stations = name.split("->");
        if (stations.length >= 2) {
            return new Station(stations[1]); // Assuming Station constructor takes station name
        } else {
            return null; // Handle invalid segment name format
        }
    }

    /** verify is a verification method that seeks to ensure a number of things are consistent in the segment.
     * @param NONE
     * @return Boolean value of true if the verification checks are accurate, false if not.
     */
    public boolean verify() {
        // Check if the segment name is valid
        if (name == null || name.trim().isEmpty()) return false;

        // Check if the traffic light is valid
        if (trafficLight == null || trafficLight.getColour() == null) return false;

        // Assuming the segment must not be occupied when it is closed
        if (status == RSStatus.CLOSED && isOccupied) return false;

        return true;
    }

    
    /** close is a method that changes the segment status to closed.
     * @param NONE
     * @return NONE
     */
    public void close() {
        status = RSStatus.CLOSED;
    }

    /** open is a method that changes the segment status to open.
     * @param NONE
     * @return NONE
     */
    public void open() {
        status = RSStatus.OPEN;
    }

    public String getName() {
        return name;
    }

    public RSStatus getStatus() {
        return status;
    }

    public Train getTrain() {
        return train;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(RSStatus status) {
        this.status = status;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
    // Other methods specific to Segment can be added
}

