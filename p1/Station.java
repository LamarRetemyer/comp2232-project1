package p1;

public class Station {
    private String name;
    private RSStatus status;
    private Train train;

    public Station(String name) {
        this.name = name;
        this.status = RSStatus.OPEN;
        this.train = null;
    }

    public boolean hasTrain() {
        return train != null;
    }

    public boolean isOpen() {
        return status == RSStatus.OPEN;
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

    public void acceptTrain(Train train) {
        if (verify()) {
            this.train = train;
        } else {
            System.out.println("Cannot accept train at this time.");
        }
    }

    public void releaseTrain() {
        train = null;
    }

    // Getters and setters
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

    enum RSStatus {
        OPEN,
        CLOSED
    }
    
}



