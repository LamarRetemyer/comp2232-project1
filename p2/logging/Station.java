package p2.logging;

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

    /** hasTrain is a method that checks to see if there is a train in the station.
     * @param NONE
     * @return boolean value of true if it is there, and false if not.
     */
    public boolean hasTrain() {
        return train != null;
    }

    /** isOpen is a method that checks to see if the station is open.
     * @param NONE
     * @return boolean value of true if the status is open, false if not.
     */
    public boolean isOpen() {
        return status == RSStatus.OPEN;
    }

    /** close is a method that sets the status of station to closed.
     * @return NONE
     * @param NONE
     */
    public void close() {
        status = RSStatus.CLOSED;
    }

    /** open is a method that sets the status of station to open.
     * @return NONE
     * @param NONE
     */
    public void open() {
        status = RSStatus.OPEN;
    }

    /** acceptTrain is a method that allow trains to enter the station once it is open and not occupied by another train.
     * @param train
     * @return
     */
    public boolean acceptTrain(Train train) {
        if (isOpen() && !hasTrain()) {
            this.train = train;
            return true;
        }
        return false;
    }

    /** releaseTrain is a method that removes any train that may be occupying the station.
     * @param NONE
     * @return
     */
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

    /** verify is a verification method that seeks to ensure a number of things are consistent in the Station.
     * @param NONE
     * @return A boolean value of true if the verification is accurate/succesful, otherwise a value of false.
     */
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
