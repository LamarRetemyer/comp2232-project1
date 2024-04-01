package p1;

public class Segment {
    private String name;
    private RSStatus status;
    private Train train;
    private TrafficLight trafficLight;

    public Segment(String name) {
        this.name = name;
        this.status = RSStatus.OPEN;
        this.train = null;
        this.trafficLight = new TrafficLight();
    }

    public boolean hasTrain() {
        return train != null;
    }

    public boolean isOpen() {
        return status == RSStatus.OPEN;
    }

    public void acceptTrain(Train train) {
        if (!hasTrain() && isOpen()) {
            this.train = train;
        } else {
            System.out.println("Cannot accept train at this time.");
        }
    }

    public void releaseTrain() {
        train = null;
    }

    public void changeLight() {
        trafficLight.change();
    }

    public boolean lightColour() {
        return trafficLight.getColour() == TrafficLight.Light.GREEN;
    }

    public boolean verify() {
        return isOpen() && !hasTrain();
    }

    public void close() {
        status = RSStatus.CLOSED;
    }

    public void open() {
        status = RSStatus.OPEN;
    }

    // Getters and setters for private fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RSStatus getStatus() {
        return status;
    }

    public void setStatus(RSStatus status) {
        this.status = status;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public void setTrafficLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    // Example usage
    public static void main(String[] args) {
        Segment segment1 = new Segment("Segment A");
        Train train1 = new Train(1);
        segment1.acceptTrain(train1);
        segment1.changeLight();
        System.out.println("Train in segment: " + segment1.hasTrain()); // Output: Train in segment: false
        System.out.println("Light colour: " + segment1.lightColour()); // Output: Light colour: true
    }
}

enum RSStatus {
    OPEN,
    CLOSED
}


}
