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
    private boolean isAtStart = true;
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

    public Event start() {
        if (status == TrainStatus.Initialised && isRegistered()) {
            status = TrainStatus.Started;
            return new TrainStartEvent("Train " + id + " started", System.currentTimeMillis());
        }
        return null;
    }

    public Event finish() {
        if (status == TrainStatus.Started) {
            status = TrainStatus.Completed;
            return new TrainFinishEvent("Train " + id + " finished", System.currentTimeMillis());
        }
        return null;
    }

    public Event advance(int time) {
        if (time > 0 && status == TrainStatus.Started) {
            this.waitTimeRemaining = Math.max(0, this.waitTimeRemaining - time);
            return new TrainAdvanceEvent("Train " + id + " advanced", System.currentTimeMillis());
        }
        return null;
    }

    // Route management
    public void changeRoute(Route r) {
        this.route = r;
    }

    public void addStop(String stop) {
        if (!stopsAt.contains(stop)) {
            stopsAt.add(stop);
        }
    }

    // Verification
    public boolean verify() {
        return isRegistered() && status != TrainStatus.Initialised;
    }

    @Override
    public int compareTo(Train train) {
        return this.name.compareTo(train.getName());
    }

    @Override
    public boolean validate() {
        return super.validate() && verify();
    }

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
