package p1;

public class Segment {
    private String name;
    private RSStatus status;
    private Train train;
    private TrafficLight trafficLight;
    private Station  start;
    private Station  sEnd;

    public Segment(String name, Station start, Station sEnd) {
        this.name = name;
        this.status = RSStatus.OPEN;
        this.train = null;
        this.trafficLight = new TrafficLight(1, Light.RED);
        this.start = start;
        this.sEnd = sEnd;
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
        return trafficLight.getColour() == Light.GREEN;
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

    public Station getStart() {
        return start;
    }

    public void setStart(Station start){
        this.start = start;
    }
    
    public Station getSEND() {
        return sEnd;
    }

  
    public void setSEnd(Station end){
        this.sEnd = end;
    }

}





