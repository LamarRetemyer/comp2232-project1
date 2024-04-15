package p2.events;

public class TrainStartEvent extends Event {
    public TrainStartEvent(String object, long time) {
        super(object, time);
    }
}