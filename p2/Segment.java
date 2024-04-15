package p2;

import p2.enums.Light;
import p2.enums.RSStatus;

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

    public boolean hasTrain() {
        return train != null;
    }

    public boolean isOpen() {
        return status == RSStatus.OPEN;
    }

    public boolean acceptTrain(Train train) {
        if (isOpen() && !hasTrain()) {
            this.train = train;
            return true;
        }
        return false;
    }

    public void releaseTrain() {
        if (hasTrain()) {
            this.train = null;
        }
    }

    public void changeLight() {
        if (this.isOccupied) {
            this.trafficLight.setColour(Light.RED);
        } else {
            this.trafficLight.setColour(Light.GREEN);
        }
    }

    
    
    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
        changeLight();
    }


    public boolean lightColour() {
        return trafficLight.getColour() == Light.GREEN;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public Station getStartStation() {
        // Assuming the segment name format is "StartStation->EndStation"
        String[] stations = name.split("->");
        if (stations.length >= 2) {
            return new Station(stations[0]); // Assuming Station constructor takes station name
        } else {
            return null; // Handle invalid segment name format
        }
    }

    public Station getEndStation() {
        // Assuming the segment name format is "StartStation->EndStation"
        String[] stations = name.split("->");
        if (stations.length >= 2) {
            return new Station(stations[1]); // Assuming Station constructor takes station name
        } else {
            return null; // Handle invalid segment name format
        }
    }

    public boolean verify() {
        // Check if the segment name is valid
        if (name == null || name.trim().isEmpty()) return false;

        // Check if the traffic light is valid
        if (trafficLight == null || trafficLight.getColour() == null) return false;

        // Assuming the segment must not be occupied when it is closed
        if (status == RSStatus.CLOSED && isOccupied) return false;

        return true;
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

