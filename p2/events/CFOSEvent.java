package p2.events;
import p2.enums.Action;

public class CFOSEvent extends Event {
    private Action action;

    public CFOSEvent(String object, int time, Action action) {
        super(object, time);
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public String toString() {
        return super.toString() + ", Action=" + action;
    }
}
