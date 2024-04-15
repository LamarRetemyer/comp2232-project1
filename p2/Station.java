package p2;

import p2.enums.RSStatus;

public class Station{
    private String name;
    private RSStatus status;
    private Train train;

    public Station(String name) {
        this.name = name;
        this.status = RSStatus.CLOSED; // Initialize with closed status
        this.train = null; // Initialize with no train
    }

    public boolean hasTrain() {
        return train != null;
    }

    public boolean isOpen() {
        return status == RSStatus.OPEN;
    }

    public void close() {
        status = RSStatus.CLOSED;
    }

    public void open() {
        status = RSStatus.OPEN;
    }

    public boolean acceptTrain(Train train) {
        if (isOpen() && !hasTrain()) {
            this.train = train;
            return true;
        }
        return false;
    }

    public boolean releaseTrain() {
        if (hasTrain()) {
            this.train = null;
            return true;
        }
        return false;
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

    public boolean verify() {
        return name != null && !name.trim().isEmpty();
    }
    

    public void setStatus(RSStatus status) {
        this.status = status;
    }

    public void setTrain(Train train) {
        this.train = train;
    }
    
}
