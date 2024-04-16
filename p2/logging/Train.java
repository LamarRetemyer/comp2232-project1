package p2.logging;

import java.util.ArrayList;
import p2.enums.TrainStatus;
import p2.events.*;

public class Train extends Logable implements Comparable<Train> {
    private int id = nextID++;
    private static int nextID = 1;
    private String name;
    private int timeRegistered = -1; // Immutable once set, reset on deregister
    private int startTime = -1; // Immutable once set
    private String currentStation;
    private Route route;
    private int waitTimeRemaining = 0;
    private ArrayList<String> stopsAt = new ArrayList<>();
    private TrainStatus status = TrainStatus.Initialised;

    public Train(String name, int startTime) {
        this.name = name != null ? name : "";
        this.startTime = Math.max(startTime, -1);
    }

    // Accessor methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStartTime() {
        return startTime;
    }

    public boolean isRegistered() {
        return timeRegistered > 0;
    }

    public int whenRegistered() {
        return timeRegistered;
    }

    // Operational methods
    public void register(int time) {
        if (status == TrainStatus.Initialised && !isRegistered()) {
            this.timeRegistered = time;
        }
    }

    public void deregister() {
        this.timeRegistered = -1;
        this.status = TrainStatus.Initialised;
    }

    /**
     * start is an event method that allows the train to start once it is registered and has gone through initialization.
     * @param NONE
     * @return The event allowing for the train to start, otherwise returns null.
     */
    public Event start() {
        if (status == TrainStatus.Initialised && isRegistered()) {
            status = TrainStatus.Started;
            return new TrainStartEvent("Train " + id + " started", System.currentTimeMillis());
        }
        return null;
    }

    /**
     * finish is an event method that finishes the train's movement.
     * @param NONE
     * @return The event allowing for the train to end, otherwise return null.
     */
    public Event finish() {
        if (status == TrainStatus.Started) {
            status = TrainStatus.Completed;
            return new TrainFinishEvent("Train " + id + " finished", System.currentTimeMillis());
        }
        return null;
    }

    /**
     * advance is a method that allows the train to advance by a specific amount of time.
     * @param time The amount of time to advance the train by.
     * @return An event showing the advancement of the train, otherwise return null.
     */
    public Event advance(int time) {
        if (time > 0 && status == TrainStatus.Started) {
            this.waitTimeRemaining = Math.max(0, this.waitTimeRemaining - time);
            return new TrainAdvanceEvent("Train " + id + " advanced", System.currentTimeMillis());
        }
        return null;
    }

    /** 
     * changeRoute is a method that allows the train's route to be changed
     * @param r The route the train will be allowed to change to.
     * @return NONE
     */
    public void changeRoute(Route r) {
        this.route = r;
    }

    /**
     * addStop is a method that adds another stop to the list of stops for a train.
     * @param stop The reference to the stop that is to be added.
     * @return NONE
     */
    public void addStop(String stop) {
        if (!stopsAt.contains(stop)) {
            stopsAt.add(stop);
        }
    }

    /**
     * verify is a method that checks and verifies a train's status in terms of registration.
     * @param NONE
     * @return true if the train is registered, false if not.
     */
    public boolean verify() {
        return isRegistered() && status != TrainStatus.Initialised;
    }

    /**
     * compareTo is a method that runs a comparison between a train and another.
     * @param train The train to be compared to.
     * @return 
     */
    @Override
    public int compareTo(Train train) {
        return this.name.compareTo(train.getName());
    }

    /**
     * validate is a method that validates the train's details for a journey.
     * @param NONE
     * @return True if the validation is successful, false if not.
     */
    @Override
    public boolean validate() {
        return super.validate() && verify();
    }

    
    /**
     * toString is a method that returns a string representation of the Train object.
     * @param NONE
     * @return A string string representation of the train object in detail.
     */
    @Override
    public String toString() {
        return "Train [id=" + id + ", name=" + name + ", timeRegistered=" +
               (timeRegistered <= 0 ? "unregistered" : timeRegistered) + ", startTime=" +
               (timeRegistered > 0 ? getStartTime() : "unregistered") + ", currentStation=" +
               (currentStation != null ? currentStation : "none") + ", route=" +
               (route != null ? route.getName() : "none") + ", stopsAt=" +
               (stopsAt.isEmpty() ? "None" : stopsAt) + ", status=" + status.getDescription() +
               ", verified=" + (verify() ? "Yes" : "No") + "]";
    }
}
